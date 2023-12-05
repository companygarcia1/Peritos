package com.example.peritos.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.peritos.databinding.FragmentAccindentListBinding
import com.example.peritos.model.DataSource
import com.example.peritos.model.accident.Accident
import com.example.peritos.model.accident.TipoDaño
import com.example.peritos.ui.NavigationManager
import com.example.peritos.ui.adapters.AccidentListAdapter

class AccidentListFragment : Fragment() {
    private lateinit var binding: FragmentAccindentListBinding
    private lateinit var ctx: Context
    private lateinit var adapter: AccidentListAdapter

    private var filter: TipoDaño? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccindentListBinding.inflate(inflater)
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
                if (item is Accident) {
                    val vehiculo: Accident = item
                    NavigationManager.openAccidentDetail(ctx, vehiculo.id)
                }
            }
    }

    private fun configView() {
        val accidentes = ArrayList<Accident>()
        adapter = AccidentListAdapter(ctx, accidentes)
        binding.listview.adapter = adapter
        binding.listview.emptyView = binding.txtEmptyview
        filter()
    }

    private fun filter() {
        var accidentes = DataSource.accidentDataSource().listAccident(ctx)
        if (filter != null) {
            accidentes = accidentes.filter { it.tipoDano == filter }
        }
        adapter.setData(accidentes)
    }

    fun filterGrave() {
        filter = TipoDaño.GRAVE
        filter()
    }

    fun filterMedium() {
        filter = TipoDaño.MEDIO
        filter()
    }

    fun filterLight() {
        filter = TipoDaño.LEVE
        filter()
    }

    fun filterAll() {
        filter = null
        filter()
    }
}