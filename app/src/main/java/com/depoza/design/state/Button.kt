package com.depoza.design.state

import java.io.Serializable

/**
 * Button description as UI component
 */
data class Button(
    val text: String,
    val textHexColor: String? = null,
    val bgHexColor: String? = null,
    val action: Action
) : Serializable {

    /**
     * Button's action description to be processed by any actions handler
     *
     * For example:
     * - type="default", value="do_some_action"
     * - type="link", value="http://example.com"
     * - type="deeplink", value="example-app://example.com"
     */
    data class Action(val type: String = "default", val value: String) : Serializable
}