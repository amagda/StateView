package com.depoza.design.state.sample

import android.content.Context
import com.depoza.design.state.Button
import com.depoza.design.state.State
import com.depoza.design.state.StatesProvider

class PermissionStatesProvider : StatesProvider {

    override fun provide(context: Context) = listOf(
        State(
            "TAG_PERMISSION_STATE_1",
            R.drawable.ic_permission_state,
            "FIRST permission state title",
            "First permission state subtitle",
            listOf(
                Button(
                    "Permission state 2",
                    "#156b62",
                    "#FFFFFF",
                    action = Button.Action(value = "ACTION_PERMISSION_STATE_2")
                )
            )
        ),

        State(
            "TAG_PERMISSION_STATE_2",
            R.drawable.ic_permission_state,
            "SECOND permission state title",
            "Second permission state subtitle",
            listOf(
                Button(
                    "Permission state 1",
                    "#156b62",
                    "#FFFFFF",
                    action = Button.Action(value = "ACTION_PERMISSION_STATE_1")
                ),
                Button(
                    "Empty states",
                    "#156b62",
                    "#FFFFFF",
                    action = Button.Action(value = "ACTION_EMPTY_STATES")
                )
            )
        )
    )
}