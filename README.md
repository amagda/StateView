# StateView
[ ![Download](https://api.bintray.com/packages/depoza/design/state-view/images/download.svg) ](https://bintray.com/depoza/design/state-view/_latestVersion)

Android View to display different customizable states (Error, Empty, Permissions and etc.)

<img src="/img/empty_state.png" width="20%" alt="Empty state sample"/> <img src="/img/empty_state_2.png" width="20%" alt="Empty state2 sample"/>



## Setup
```java
dependencies {
    implementation 'com.github.kobeumut:StateView:0.2'
}
```


## How to use
#### Single state
###### Useful when you need to generate a specific state at runtime
```kotlin
// 1. Create state
val state = State(
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
)

// 2. Request to display state
stateView.apply(state)
```


#### Multi states
###### Useful when you need to register a set of available states and switch between them
```kotlin
// 1. Create list of states
val states = listOf(state1, state2)

// 2. Register available states
stateView.states(states)

// 3. Requests to display one of the registered states by tag
stateView.apply("TAG_EMPTY_STATE_1")
```


#### StatesProvider
###### Useful when you want to register a set of available states into xml-layout
```kotlin
// 1. Implement StatesProvider
class MyStatesProvider : StatesProvider {

    val state1 = ...
    val state2 = ...

    override fun provide(context: Context) = listOf(state1, state2)
}
```
```xml
<!-- 2. Declare attr 'states_provider'-->
<com.depoza.design.state.StateView
         ...
         app:states_provider="com.example.MyStatesProvider"/>
```


#### Handling button actions
```kotlin
stateView.buttonClickHandler { state: State, button: Button ->
    when (button.action.value) {
        "ACTION_1" -> stateView.apply("TAG_EMPTY_STATE_2")
        "ACTION_2" -> stateView.apply("TAG_EMPTY_STATE_1")
    }
}
```


#### Custom layout
###### Useful when you want to save default logic but change layout

<img src="/img/permission_state.png" width="20%" alt="Permission state sample"/>

```kotlin
// 1. Create custom view and extends StateView
// 2. Override method getLayoutResId()
// 3. To save default logic use views with specific ids
class MyAppStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.stateViewStyle
) : StateView(context, attrs, defStyleAttr) {

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
    override fun getLayoutResId() = R.layout.myApp_state_view
}
```




## Styles/Theme
#### Supported attrs
```xml
<declare-styleable name="StateView">
    <attr name="img_width" format="dimension"/>
    <attr name="img_height" format="dimension"/>
    <attr name="title_textAppearance" format="reference"/>
    <attr name="subtitle_textAppearance" format="reference"/>
    <attr name="states_provider" format="string"/>
</declare-styleable>
```


#### Customize widget styles
```xml
<style name="Widget.MyApp.StateView" parent="Widget.Depoza.Design.StateView">
    <item name="img_width">@dimen/myApp_img_width</item>
    <item name="img_height">@dimen/myApp_img_height</item>
    <item name="title_textAppearance">@style/TextAppearance.MyApp.Widget.StateView.Title</item>
    <item name="subtitle_textAppearance">@style/TextAppearance.MyApp.Widget.StateView.Subtitle</item>
</style>

<style name="TextAppearance.MyApp.Widget.StateView.Title" parent="TextAppearance.Depoza.Design.Widget.StateView.Title">
    <item name="android:textSize">@dimen/myApp_title_txt_size</item>
    <item name="android:textColor">@color/myApp_title_color</item>
</style>

<style name="TextAppearance.MyApp.Widget.StateView.Subtitle" parent="TextAppearance.Depoza.Design.Widget.StateView.Subtitle">
    <item name="android:textSize">@dimen/myApp_subtitle_txt_size</item>
    <item name="android:textColor">@color/myApp_subtitle_color</item>
</style>
```

#### Customize the default theme
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.MyApp" parent="Theme.AppCompat">
        <item name="stateViewStyle">@style/Widget.MyApp.StateView</item>
    </style>
</resources>
```




## Proguard
If you want to register a set of available states into xml-layout by declaring attr 'states_provider'
```kotlin
-keep class * implements com.depoza.design.state.StatesProvider
```
