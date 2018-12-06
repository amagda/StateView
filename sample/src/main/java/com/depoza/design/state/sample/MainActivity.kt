package com.depoza.design.state.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.depoza.design.state.Button
import com.depoza.design.state.State
import com.depoza.design.state.StateView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var emptyStatesView: StateView
    private lateinit var permissionStatesView: PermissionStateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEmptyStatesView()
        initPermissionStatesView()

        // Requests to display specific state by tag
        emptyStatesView.apply("TAG_EMPTY_STATE_1")
        emptyStatesView.visibility = View.VISIBLE
    }

    /**
     * Example of providing:
     * - available states programmatically
     * - button clicks handler
     */
    private fun initEmptyStatesView() {
        var empty = EmptyStatesProvider().provide(this)
        emptyStatesView = findViewById(R.id.empty_states_view)
        emptyStatesView.tv.setOnClickListener {
            empty.first().title = "Changed Title"
            val test = empty
            emptyStatesView.states(test)
            emptyStatesView.apply("TAG_EMPTY_STATE_1")
        }
        // Provides available states
        emptyStatesView.states(empty)

        // Provides button clicks handler
        emptyStatesView.buttonClickHandler { state: State, button: Button ->
            when (button.action.value) {
                "ACTION_1" -> emptyStatesView.apply("TAG_EMPTY_STATE_2")
                "ACTION_2" -> emptyStatesView.apply("TAG_EMPTY_STATE_1")
                "HIDE" -> emptyStatesView.hide()
                "ACTION_PERMISSION_STATES" -> {
//                    emptyStatesView.visibility = View.GONE
                    permissionStatesView.visibility = View.VISIBLE
                    permissionStatesView.background.alpha = 140
                    permissionStatesView.apply("TAG_PERMISSION_STATE_1")

                }
            }
        }
    }

    /**
     * Example of custom StateView with providing:
     * - available states via attribute 'states_provider' declaration into the layout xml-file
     * - button clicks handler
     */
    private fun initPermissionStatesView() {
        permissionStatesView = findViewById(R.id.permission_states_view)

        // Provides button clicks handler
        permissionStatesView.buttonClickHandler { state: State, button: Button ->
            when (button.action.value) {
                "ACTION_1" -> permissionStatesView.apply("TAG_PERMISSION_STATE_2")
                "ACTION_2" -> permissionStatesView.apply("TAG_PERMISSION_STATE_1")
                "HIDE" -> {
                    permissionStatesView.hide(permissionStatesView)// if more than one views, hide accept view parameter
                    emptyStatesView.visibility = View.VISIBLE
                }
                "ACTION_EMPTY_STATES" -> {
                    permissionStatesView.visibility = View.GONE
                    emptyStatesView.visibility = View.VISIBLE
                    emptyStatesView.apply("TAG_EMPTY_STATE_1")
                }
            }
        }
    }
}
