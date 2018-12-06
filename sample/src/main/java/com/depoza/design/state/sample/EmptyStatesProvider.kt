package com.depoza.design.state.sample

import android.content.Context
import com.depoza.design.state.Button
import com.depoza.design.state.State
import com.depoza.design.state.StatesProvider

class EmptyStatesProvider : StatesProvider {

    override fun provide(context: Context) = mutableListOf(
        State(
            tag = "TAG_EMPTY_STATE_1",
            imgResId = R.drawable.ic_empty_state_1,
            title = "FIRST empty state title",
            subtitle = "First empty state subtitle",
            buttons = listOf(
                Button(
                    text = "Action 1",
                    textHexColor = "#FFFFFF",
                    bgHexColor = "#773CA3",
                    action = Button.Action(type = "custom", value = "ACTION_1")
                )
            )
        ),

        State(
            tag = "TAG_EMPTY_STATE_2",
            imgResId = R.drawable.ic_empty_state_2,
            title = "SECOND empty state title",
            subtitle = "Second empty state subtitle",
            buttons = listOf(
                Button(
                    text = "Action 2",
                    textHexColor = "#FFFFFF",
                    bgHexColor = "#FF6329",
                    action = Button.Action(type = "custom", value = "ACTION_2")
                ),
                Button(
                    text = "Hide",
                    textHexColor = "#FFFFFF",
                    bgHexColor = "#FF6329",
                    action = Button.Action(type = "custom", value = "HIDE")
                ),
                Button(
                    text = "Permission states",
                    textHexColor = "#FFFFFF",
                    bgHexColor = "#156B62",
                    action = Button.Action(type = "custom", value = "ACTION_PERMISSION_STATES")
                )
            )
        )
    )
}