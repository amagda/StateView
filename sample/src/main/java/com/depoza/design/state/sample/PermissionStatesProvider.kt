package com.depoza.design.state.sample

import android.content.Context
import com.depoza.design.state.Button
import com.depoza.design.state.State
import com.depoza.design.state.StatesProvider

class PermissionStatesProvider : StatesProvider {

    override fun provide(context: Context) = mutableListOf(
        State(
            tag = "TAG_PERMISSION_STATE_1",
            imgResId = R.drawable.ic_permission_state,
            title = "FIRST permission state title",
            subtitle = "First permission state subtitle",
            buttons = listOf(
                Button(
                    text = "Action 1",
                    textHexColor = "#156b62",
                    bgHexColor = "#FFFFFF",
                    action = Button.Action(type = "custom", value = "ACTION_1")
                )
            )
        ),

        State(
            tag = "TAG_PERMISSION_STATE_2",
            imgResId = R.drawable.ic_permission_state,
            title = "SECOND permission state title",
            subtitle = "Second permission state subtitle",
            buttons = listOf(
                Button(
                    text = "Action 2",
                    textHexColor = "#156b62",
                    bgHexColor = "#FFFFFF",
                    action = Button.Action(type = "custom", value = "ACTION_2")
                ),
                Button(
                    text = "Hide",
                    textHexColor = "#156b62",
                    bgHexColor = "#FFFFFF",
                    action = Button.Action(type = "custom", value = "HIDE")
                ),
                Button(
                    text = "Empty states",
                    textHexColor = "#156b62",
                    bgHexColor = "#FFFFFF",
                    action = Button.Action(type = "custom", value = "ACTION_EMPTY_STATES")
                )
            )
        )
    )
}