package com.depoza.design.state

import android.content.Context
import android.support.annotation.ColorInt
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.AppCompatButton
import android.widget.Button

/**
 * Creates Android Button widget based on [Button] description
 */
fun com.depoza.design.state.Button.createView(context: Context): Button {
    val result = AppCompatButton(context)
    result.text = this.text
    this.textHexColor?.let { result.setTextColor(it.toIntColor()) }
    this.bgHexColor.takeIf { !it.isNullOrBlank() }?.let {
        result.applyBackgroundColor(it.toIntColor())
    }
    return result
}

/**
 * Applies new [color] to the Android Button widget
 */
fun Button.applyBackgroundColor(@ColorInt color: Int) {
    val btnDrawable = DrawableCompat.wrap(this.background)
    DrawableCompat.setTint(btnDrawable, color)
    ViewCompat.setBackground(this, btnDrawable)
}
