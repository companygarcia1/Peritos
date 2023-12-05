package com.example.peritos.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.peritos.databinding.AccidentAdapterItemBinding
import com.example.peritos.databinding.VehicleAdapterItemBinding
import com.example.peritos.model.accident.Accident
import com.example.peritos.model.vehicle.Vehicle

class VehicleListAdapter(var context: Context, private var list:  List<Vehicle>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val item = this.list[position]
        val inflator = context!!.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater
        val binding = VehicleAdapterItemBinding.inflate(inflator)

        binding.txtLicense.text = item.matricula
        binding.txtModel.text = item.tipoVehiculo.name
        return binding.root
    }

    fun setData(cars: List<Vehicle>) {
        this.list = cars
        notifyDataSetChanged()
    }
}