package com.depoza.design.state

import android.content.Context

/**
 * Provider of [State] list
 */
@FunctionalInterface
interface StatesProvider {
    fun provide(context: Context): MutableList<State>
}