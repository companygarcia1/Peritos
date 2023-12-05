package com.example.peritos.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.peritos.databinding.FragmentAccindentListBinding
import com.example.peritos.model.DataSource
import com.example.peritos.ui.adapters.AccidentListAdapter

class AccidentListFragment : Fragment() {
    private lateinit var binding: FragmentAccindentListBinding
    private lateinit var ctx: Context
    private lateinit var adapter: AccidentListAdapter

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

    }

    private fun configView() {
        val accidentes = DataSource.accidentDataSource().listAccident(ctx)
        adapter = AccidentListAdapter(ctx, accidentes)
        binding.listview.adapter = adapter
        binding.listview.emptyView = binding.txtEmptyview
        if (accidentes.isEmpty()) {
            binding.txtEmptyview.setText("No hay accidentes")
        }
    }
}