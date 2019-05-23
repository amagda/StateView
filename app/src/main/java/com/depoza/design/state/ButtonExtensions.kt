package com.depoza.design.state

import android.content.Context
import android.widget.Button
import com.google.android.material.button.MaterialButton

/**
 * Creates Android Button widget based on [Button] description
 */
fun com.depoza.design.state.Button.createView(context: Context): Button {
    val btn = this
    return MaterialButton(context).run {
        text = btn.text
        isAllCaps = true
        if (!btn.textHexColor.isNullOrBlank()) setTextColor(btn.textHexColor.toIntColor())
        if (!btn.bgHexColor.isNullOrBlank()) setBackgroundColor(btn.bgHexColor.toIntColor())
        setRippleColorResource(R.color.state_view_btn_ripple_color)
        this
    }
}