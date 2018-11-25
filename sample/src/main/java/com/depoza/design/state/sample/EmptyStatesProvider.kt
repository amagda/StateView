package com.depoza.design.state.sample

import android.content.Context
import com.depoza.design.state.Button
import com.depoza.design.state.State
import com.depoza.design.state.StatesProvider

class EmptyStatesProvider : StatesProvider {

    override fun provide(context: Context) = listOf(
        State(
            "TAG_EMPTY_STATE_1",
            R.drawable.ic_empty_state_1,
            "FIRST empty state title",
            "First empty state subtitle",
            listOf(
                Button(
                    "Empty state 2",
                    "#FFFFFF",
                    "#773CA3",
                    action = Button.Action(value = "ACTION_EMPTY_STATE_2")
                )
            )
        ),

        State(
            "TAG_EMPTY_STATE_2",
            R.drawable.ic_empty_state_2,
            "SECOND empty state title",
            "Second empty state subtitle",
            listOf(
                Button(
                    "Empty state 1",
                    "#FFFFFF",
                    "#FF6329",
                    Button.Action(value = "ACTION_EMPTY_STATE_1")
                ),
                Button(
                    "Permission states",
                    "#FFFFFF",
                    "#156B62",
                    action = Button.Action(value = "ACTION_PERMISSION_STATES")
                )
            )
        )
    )
}