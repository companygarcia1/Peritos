package com.example.peritos.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.peritos.databinding.AccidentAdapterItemBinding
import com.example.peritos.model.DataSource
import com.example.peritos.model.accident.Accident
import com.example.peritos.utils.FileManager

class AccidentListAdapter(var context: Context,private var list:  List<Accident>) : BaseAdapter() {

    override fun getCount(): Int {
        return this.list.size
    }

    override fun getItem(position: Int): Any {
        return this.list[position]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val item = this.list[position]
        val inflator = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater
        val binding = AccidentAdapterItemBinding.inflate(inflator)

       // binding.txtLicense.text = item.vehiculo
        binding.txtModel.text = item.tipoDano.name
        binding.imageView.setImageBitmap(FileManager.loadBitmapFromFilePath(item.foto))
        val vehicle = DataSource.vehicleDataSource().vehiculo(context,item.vehiculoId)
        binding.txtLicense.text = vehicle?.matricula
        return binding.root
    }

    fun setData(cars: List<Accident>) {
        this.list = cars
        notifyDataSetChanged()
    }
}