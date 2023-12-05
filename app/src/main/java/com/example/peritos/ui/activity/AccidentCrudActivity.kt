package com.example.peritos.ui.activity

import android.Manifest
import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.DateFormat
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.peritos.databinding.ActivityAccidentCrudBinding
import com.example.peritos.model.DataSource
import com.example.peritos.model.accident.Accident
import com.example.peritos.model.accident.TipoDaño
import com.example.peritos.model.vehicle.Vehicle
import com.example.peritos.utils.DialogManager
import com.example.peritos.utils.FileManager
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Date

class AccidentCrudActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccidentCrudBinding

    private val MY_PERMISSIONS_REQUEST_CAPTURE_CAMERA = 1223432
    private val MY_PERMISSIONS_REQUEST_CODE = 123456

    private var photo: String? = null
    private var date: Date? = null
    private var vehicle: Vehicle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentCrudBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configView()
        setListener()
    }

    private fun configView() {
        setSupportActionBar(binding.myToolbar)

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            TipoDaño.values().map { it.name }
        )
        binding.spinner.setAdapter(adapter)

        val vehicles = DataSource.vehicleDataSource().listarVehiculos(this)
        val adapterVehicle = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            vehicles.map { "${it.id} - ${it.matricula}" }
        )

        binding.spinnerVehicle.setAdapter(adapterVehicle)
    }

    private fun setListener() {
        binding.imgButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA),
                    MY_PERMISSIONS_REQUEST_CAPTURE_CAMERA
                )
            } else {
                // Si tiene condedido el permiso previamente
                val intent = Intent(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                startActivityForResult(intent, MY_PERMISSIONS_REQUEST_CODE)
            }
        }
        binding.button.setOnClickListener {
            val address = binding.editMap.text.toString()
            val phone = binding.editPhone.editText?.text.toString()
            val typeAccident = binding.editType.editText?.text.toString()
            val comentario = binding.editComments.editText?.text.toString()

            saveAccident(photo, date, vehicle, address, phone, typeAccident, comentario)
        }

        binding.editDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.addOnPositiveButtonClickListener {
                date = Date(it)
                binding.editDate.setText(DateFormat.format("dd-MM-yyyy", date).toString())
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }

        binding.spinnerVehicle.setOnItemClickListener { adapterView, view, i, l ->
            val vehicles = DataSource.vehicleDataSource().listarVehiculos(this)
            vehicle = vehicles[i]
        }
    }

    private fun saveAccident(
        photo: String?,
        date: Date?,
        vehicle: Vehicle?,
        address: String,
        phone: String,
        typeAccident: String,
        comentario: String
    ) {
        if (controlAccident(photo, date, vehicle, address, phone, typeAccident, comentario)) {
            DataSource.accidentDataSource().addAccident(
                this,
                Accident(
                    0,
                    address,
                    date!!,
                    vehicle!!.id,
                    photo!!,
                    comentario,
                    phone,
                    TipoDaño.valueOf(typeAccident)
                )
            )
            finish()
        }
    }

    private fun controlAccident(
        photo: String?,
        date: Date?,
        vehicle: Vehicle?,
        address: String,
        phone: String,
        typeAccident: String,
        comentario: String
    ): Boolean {
        var accepted = true
        if (photo == null) {
            DialogManager.alertDialog(
                this,
                "Faltan datos",
                "El campo foto no puede estar vacio."
            )
            accepted = false
        }
        if (date == null) {
            DialogManager.alertDialog(this, "Faltan datos", "El campo fecha no puede estar vacio.")
            accepted = false
        }
        if (vehicle == null) {
            DialogManager.alertDialog(
                this,
                "Faltan datos",
                "El campo vehiculo no puede estar vacio."
            )
            accepted = false
        }
        if (typeAccident.isEmpty()) {
            DialogManager.alertDialog(
                this,
                "Faltan datos",
                "El campo tipo de accidente no puede estar vacio."
            )
            accepted = false
        }
        return accepted
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAPTURE_CAMERA -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Log.d("DEBUG", "Permiso concedido!!")
                    val intent = Intent(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                    startActivityForResult(intent, MY_PERMISSIONS_REQUEST_CODE)
                } else {
                    Log.d("DEBUG", "Permiso rechazado!!")
                }
                return
            }

            else -> {
                Log.d("DEBUG", "Se pasa de los permisos.")
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === MY_PERMISSIONS_REQUEST_CODE && resultCode === RESULT_OK) {
            val thumbnail: Bitmap = data?.getParcelableExtra("data")!!
            binding.imgButton.setImageBitmap(thumbnail)
            photo = FileManager.saveBitmapToFile(this, thumbnail,"photo.png")
        }
    }
}