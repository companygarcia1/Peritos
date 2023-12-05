package com.example.peritos.ui

import android.content.Context
import android.content.Intent
import com.example.peritos.ui.activity.AccidentCrudActivity
import com.example.peritos.ui.activity.AccidentDetailActivity
import com.example.peritos.ui.activity.VehicleCrudActivity
import com.example.peritos.ui.activity.VehicleDetailActivity

object NavigationManager {
    fun openAccidentDetail(ctx: Context, id :Int) {
        val intent = Intent(ctx, AccidentDetailActivity::class.java)
        intent.putExtra(AccidentDetailActivity.EXTRA_ID, id)
        ctx.startActivity(intent)
    }

    fun openAccidentCreate(ctx: Context) {
        val intent = Intent(ctx, AccidentCrudActivity::class.java)
        ctx.startActivity(intent)
    }

    fun openVehicleDetail(ctx: Context, id: Int) {
        val intent = Intent(ctx, VehicleDetailActivity::class.java)
        intent.putExtra(VehicleDetailActivity.EXTRA_ID, id)
        ctx.startActivity(intent)
    }
    fun openVehicleCreate(ctx: Context) {
        val intent = Intent(ctx, VehicleCrudActivity::class.java)
        ctx.startActivity(intent)
    }

    fun openVehicleUpdate(ctx: Context, id: Int) {
        val intent = Intent(ctx, VehicleCrudActivity::class.java)
        intent.putExtra(VehicleCrudActivity.EXTRA_ID, id)
        ctx.startActivity(intent)
    }

    fun openAccidentUpdate(ctx: Context, id :Int) {
        val intent = Intent(ctx, AccidentCrudActivity::class.java)
        intent.putExtra(AccidentCrudActivity.EXTRA_ID, id)
        ctx.startActivity(intent)
    }
}