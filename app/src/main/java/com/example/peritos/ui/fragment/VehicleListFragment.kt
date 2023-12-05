package com.example.peritos.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.peritos.databinding.FragmentAccindentListBinding
import com.example.peritos.databinding.FragmentVehicleListBinding
import com.example.peritos.model.DataSource
import com.example.peritos.model.vehicle.Vehicle
import com.example.peritos.ui.NavigationManager
import com.example.peritos.ui.adapters.AccidentListAdapter
import com.example.peritos.ui.adapters.VehicleListAdapter

class VehicleListFragment : Fragment() {
    private lateinit var binding: FragmentVehicleListBinding
    private lateinit var ctx: Context
    private lateinit var adapter: VehicleListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehicleListBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        configView()
        setListener()
    }

    private fun setListener() {
        binding.listview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = adapter.getItem(position)
                if (item is Vehicle) {
                    val vehiculo: Vehicle = item
                    NavigationManager.openVehicleDetail(ctx, vehiculo.id)
                }
            }
    }

    private fun configView() {
        val vehicles = DataSource.vehicleDataSource().listarVehiculos(ctx)
        adapter = VehicleListAdapter(ctx, vehicles)
        binding.listview.adapter = adapter
        binding.listview.emptyView = binding.txtEmptyview
        if (vehicles.isEmpty()) {
            binding.txtEmptyview.text = "No hay accidentes"
        }
    }
}