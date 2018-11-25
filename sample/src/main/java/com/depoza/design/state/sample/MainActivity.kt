package com.depoza.design.state.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.depoza.design.state.Button
import com.depoza.design.state.State
import com.depoza.design.state.StateView

class MainActivity : AppCompatActivity() {

    private lateinit var emptyStatesView: StateView
    private lateinit var permissionStatesView: PermissionStateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEmptyStatesView()
        initPermissionStatesView()

        if (savedInstanceState == null) {
            // Requests to display specific state by tag
            emptyStatesView.apply("TAG_EMPTY_STATE_1")
            emptyStatesView.visibility = View.VISIBLE
        }
    }

    /**
     * Example of providing
     * - available states programmatically
     * - button clicks handler
     */
    private fun initEmptyStatesView() {
        emptyStatesView = findViewById(R.id.empty_states_view)

        // Provides available states
        emptyStatesView.states(EmptyStatesProvider().provide(this))

        // Provides button clicks handler
        emptyStatesView.buttonClickHandler { state: State, button: Button ->
            when (button.action.value) {
                "ACTION_EMPTY_STATE_1" -> emptyStatesView.apply("TAG_EMPTY_STATE_1")
                "ACTION_EMPTY_STATE_2" -> emptyStatesView.apply("TAG_EMPTY_STATE_2")
                "ACTION_PERMISSION_STATES" -> {
                    emptyStatesView.visibility = View.GONE
                    permissionStatesView.visibility = View.VISIBLE
                    permissionStatesView.apply("TAG_PERMISSION_STATE_1")
                }
            }
        }
    }

    /**
     * Example of custom StateView with providing
     * - available states via attribute 'states_provider' declaration into the layout xml-file
     * - button clicks handler
     */
    private fun initPermissionStatesView() {
        permissionStatesView = findViewById(R.id.permission_states_view)

        // Provides button clicks handler
        permissionStatesView.buttonClickHandler { state: State, button: Button ->
            when (button.action.value) {
                "ACTION_PERMISSION_STATE_1" -> permissionStatesView.apply("TAG_PERMISSION_STATE_1")
                "ACTION_PERMISSION_STATE_2" -> permissionStatesView.apply("TAG_PERMISSION_STATE_2")
                "ACTION_EMPTY_STATES" -> {
                    permissionStatesView.visibility = View.GONE
                    emptyStatesView.visibility = View.VISIBLE
                    emptyStatesView.apply("TAG_EMPTY_STATE_1")
                }
            }
        }
    }
}
