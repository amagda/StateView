package com.depoza.design.state

import android.support.annotation.DrawableRes
import java.io.Serializable

/**
 * State description as UI component
 */
data class State(
    val tag: String,
    @DrawableRes val imgResId: Int? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val buttons: List<Button>? = null
) : Serializable
