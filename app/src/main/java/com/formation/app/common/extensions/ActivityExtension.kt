package com.formation.app.common.extensions

import android.app.Activity
import com.formation.app.R
import com.formation.app.databinding.ViewAlerterInfoBinding
import com.tapadoo.alerter.Alerter

fun showAlerter(context: Activity, message: String, color: Int) {
    Alerter.create(context, R.layout.view_alerter_info)
        .setBackgroundColorRes(color)
        .also { alerter ->
            alerter.getLayoutContainer()?.let { view ->
                val binding = ViewAlerterInfoBinding.bind(view)
                binding.tvInfo.text = message
            }
        }
        .show()
}