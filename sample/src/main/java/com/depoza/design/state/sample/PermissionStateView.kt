package com.depoza.design.state.sample

import android.content.Context
import android.util.AttributeSet
import com.depoza.design.state.R
import com.depoza.design.state.StateView

class PermissionStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.stateViewStyle
) : StateView(context, attrs, defStyleAttr) {

    override fun getLayoutResId() = com.depoza.design.state.sample.R.layout.permission_state_view
}