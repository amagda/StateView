package com.depoza.design.state

import androidx.annotation.DrawableRes
import java.io.Serializable

/**
 * State description as UI component
 */
data class State(
    var tag: String,
    @DrawableRes var imgResId: Int? = null,
    var title: String? = null,
    var subtitle: String? = null,
    var buttons: List<Button>? = null
) : Serializable
