package com.depoza.design.state

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Converts color-int to the corresponding color-string into the hex-format(for example #000000)
 */
fun Int.toHexColor() = "#${Integer.toHexString(this)}"

/**
 * Converts color-string into the hex-format(for example #000000) to the corresponding color-int
 */
@ColorInt
fun String.toIntColor() = try {
    Color.parseColor(this)
} catch (e: IllegalArgumentException) {
    throw IllegalStateException("${e.message}: $this not into the color-string hex-format(for example #000000)")
}
