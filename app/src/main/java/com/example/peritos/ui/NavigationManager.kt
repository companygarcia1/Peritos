package com.example.peritos.ui

import android.content.Context
import android.content.Intent
import com.example.peritos.ui.activity.AccidentDetailActivity

object NavigationManager {
    fun openAccidentDetail(ctx: Context, id :Int) {
        val intent = Intent(ctx, AccidentDetailActivity::class.java)
        intent.putExtra(AccidentDetailActivity.EXTRA_ID, id)
        ctx.startActivity(intent)
    }

    fun openAccidentCreate(ctx: Context) {
        val intent = Intent(ctx, AccidentDetailActivity::class.java)
        ctx.startActivity(intent)
    }
}