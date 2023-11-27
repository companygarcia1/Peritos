package com.example.peritos.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peritos.R
import com.example.peritos.databinding.ActivityHomeBinding
import com.example.peritos.ui.NavigationManager
import com.example.peritos.ui.adapters.FragmentAdapter
import com.example.peritos.ui.fragment.AccidentListFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configView()
        setListener()
    }
    private fun configView(){
        val adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(AccidentListFragment(),"mi fragment")

        binding.viewPager2.adapter = adapter
        // binding.viewPager2.setPageTransformer(DepthPageTransformer())

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }

    private fun setListener(){
        binding.button.setOnClickListener {
           NavigationManager.openAccidentDetail(this,17)

        }
    }
}