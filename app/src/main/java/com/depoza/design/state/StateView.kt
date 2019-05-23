package com.depoza.design.state

import android.content.Context
import android.content.res.TypedArray
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.depoza.design.state.R.style.Widget_Depoza_Design_StateView
import com.depoza.design.state.R.styleable.*

open class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.stateViewStyle
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var curState: State? = null
    private var availableStates: List<State>? = null
    private var onBtnClickHandler: ((State, Button) -> Unit)? = null

    var imgView: AppCompatImageView? = null
    var titleTextView: TextView? = null
    var subtitleTextView: TextView? = null
    var buttonsParentView: ViewGroup? = null

    init {
        inflate(context, getLayoutResId(), this)
        imgView = findViewById(R.id.img_state_view)
        titleTextView = findViewById(R.id.title_state_view)
        subtitleTextView = findViewById(R.id.subtitle_state_view)
        buttonsParentView = findViewById(R.id.buttons_parent_state_view)
        processAttrs(context, attrs, defStyleAttr)
    }

    /**
     * Sets available states
     */
    fun states(states: List<State>) {
        availableStates = states
    }

    /**
     * Sets listener used to dispatch a callback when a button is clicked
     */
    fun buttonClickHandler(handler: ((State, Button) -> Unit)?) {
        if (handler == onBtnClickHandler) return
        onBtnClickHandler = handler

        if (curState?.buttons == null) return
        buttonsParentView?.let { parentView ->
            for (i in 0..parentView.childCount) {
                val view = parentView.getChildAt(i)
                view.setOnClickListener { handler?.invoke(curState!!, view.tag as Button) }
            }
        }
    }

    /**
     * Applies one of the available states by specific tag.
     *
     * To register available states programmatically call [states]
     * or use [app:states_provider] attribute from xml.
     * @throws IllegalStateException If there is no available states with specific tag
     */
    fun apply(stateTag: String) {
        val state = availableStates?.firstOrNull { it.tag == stateTag }
        if (state == null) {
            throw IllegalStateException(
                "There is no available states with tag '$stateTag'. " +
                        "Call states() method before to register available states"
            )
        } else {
            apply(state)
        }
    }

    /**
     * Applies new state
     */
    open fun apply(state: State) {
        if (state == curState) return
        curState = state
        refreshImage(state)
        refreshTitle(state)
        refreshSubtitle(state)
        refreshButtons(state)
    }


    /**
     * Obtains layout resource id to inflate view from.
     *
     * Override if your want to provide custom layout.
     * To save default logic use views with ids:
     * - image with [@+id/img_state_view]
     * - title with [@+id/title_state_view]
     * - subtitle with [@+id/subtitle_state_view]
     * - buttons parent with [@+id/buttons_parent_state_view]
     */
    @LayoutRes
    protected open fun getLayoutResId() = R.layout.state_view

    /**
     * Creates Android Button widget based on [Button] description
     */
    protected open fun createButtonView(btn: Button) = btn.createView(context)

    private fun refreshTitle(state: State) {
        titleTextView?.text = state.title
    }

    private fun refreshSubtitle(state: State) {
        subtitleTextView?.text = state.subtitle
    }

    private fun refreshImage(state: State) {
        imgView?.let { view ->
            view.visibility = GONE
            state.imgResId?.let { resId ->
                view.setImageResource(resId)
                view.visibility = VISIBLE
            }
        }
    }

    private fun refreshButtons(state: State) {
        buttonsParentView?.let { parentView ->
            parentView.removeAllViews()

            state.buttons?.forEach { btn ->
                val view = createButtonView(btn)
                view.tag = btn
                parentView.addView(view)

                onBtnClickHandler?.let { handler ->
                    view.setOnClickListener { handler.invoke(state, btn) }
                }
            }
        }
    }

    //region Attributes processing
    private fun processAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        context.theme.obtainStyledAttributes(attrs, StateView, defStyleAttr, Widget_Depoza_Design_StateView)
            ?.apply {
                var needRequestLayout = 0
                for (i in 0..indexCount) {
                    val attr = getIndex(i)
                    when (attr) {
                        StateView_img_width -> needRequestLayout += if (processAttrImgWidth(attr)) 1 else 0
                        StateView_img_height -> needRequestLayout += if (processAttrImgHeight(attr)) 1 else 0
                        StateView_title_textAppearance -> processAttrTitleTextAppearance(attr)
                        StateView_subtitle_textAppearance -> processAttrSubtitleTextAppearance(attr)
                        StateView_states_provider -> processAttrStatesProvider(attr, context)
                    }
                }
                recycle()
                if (needRequestLayout > 0) requestLayout()
            }
    }

    private fun TypedArray.processAttrImgWidth(attr: Int): Boolean {
        imgView?.layoutParams?.apply {
            width = getDimensionPixelSize(attr, width)
            return true
        }
        return false
    }

    private fun TypedArray.processAttrImgHeight(attr: Int): Boolean {
        imgView?.layoutParams?.apply {
            height = getDimensionPixelSize(attr, height)
            return true
        }
        return false
    }

    private fun TypedArray.processAttrTitleTextAppearance(attr: Int) =
        titleTextView?.setTextAppearance(context, getResourceId(attr, 0))

    private fun TypedArray.processAttrSubtitleTextAppearance(attr: Int) =
        subtitleTextView?.setTextAppearance(context, getResourceId(attr, 0))

    private fun TypedArray.processAttrStatesProvider(attr: Int, context: Context) {
        getString(attr)?.let {
            val availableStates = (Class.forName(it).getConstructor().newInstance() as StatesProvider).provide(context)
            states(availableStates)
        }
    }
    //endregion
}