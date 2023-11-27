package com.example.peritos.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.peritos.databinding.AccidentAdapterItemBinding
import com.example.peritos.model.accident.Accident

class AccidentListAdapter() : BaseAdapter() {
    private lateinit var context: Context
    private lateinit var list:  List<Accident>

    constructor(context: Context, list:  List<Accident>) : this() {
        this.context = context
        this.list = list
    }

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
        val inflator = context!!.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater
        val binding = AccidentAdapterItemBinding.inflate(inflator)

        binding.txtLicense.text = item.vehiculo.matricula
        binding.txtModel.text = item.tipoDaño.name
        return binding.root
    }

    fun setData(cars: List<Accident>) {
        this.list = cars
    }
}