# Throw all XML layout away, you can use kotlin `Anko` to layout all stuff

there's lots of easy way to create layout with `Anko`, just the doc stuff is not enough though.
so with this repository you can start to use this magic thing much more easier.

## what is Anko doing in my opinion 
Anko actually is way to write layout by kotlin code. in this way, anko can save some time which is used to parse xml layout resources
and with anko helper methods, you will not suffer the pain coding in java. like `verticalLayout{ button("hello button){toast("yo")}}`.
with this code you got a vertical linearlayout with a button in it, and you can click the button to get a toast saying "yo".
it's much more easier right?

also it provids some more other ways to make codeing easier. you can get rid of `Handler` to do asyn task like this
```kotlin
async() {
    // Long background task
    uiThread {
        result.text = "Done"
    }
}
```
it really looks like Swift if you know about ios app development.

generally, Anko is a way to do layout in code. and with lots of helper methods, it's every easy to do so.

## about this repository
because of the lack of doc, you can see the Anko team doc [here](https://github.com/Kotlin/anko). there's really not much to refer to.
so i have to do some research to see how to do things i want to do. maybe it's not the best way to do that.

this sample app is used to record diaries locally. but as a sample there's no sqlite, just stored them in memory.
in the first tab to see diary list, the second tab to write one. and users can select another date here. the third tab is a placeholder.
but the third tab is more important to read. cuz the seperate layout file is used to highlight that seperated `Anko` layout
can be used in lots activities and fragments. there's a pitty that this stuff is just more improved for `Activity` only.

##tabs in activity and fragments
###activity
activity with tabs in it. it's main layout looks like this:
```kotlin
relativeLayout {
    id = ID_RELATIVELAYOUT
    
    linearLayout {
        id = ID_TOP_BAR
        backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
        orientation = LinearLayout.HORIZONTAL

        titleTextView = textView {
            text = "Title"
            gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
        }.lparams {
        }
    }.lparams {
        width = matchParent
        height = dip(50)
        alignParentTop()
    }

    // .....divider......
    
    linearLayout {
        id = ID_BOTTOM_TAB_BAR
        orientation = LinearLayout.HORIZONTAL

        homeListTab = weightTextView {
            text = "List"
            onClick { tabClick(0) }
        }

        detailTab = weightTextView {
            text = "Detail"
            onClick { tabClick(1) }
        }

        settingsTab = weightTextView {
            text = "Settings"
            onClick { tabClick(2) }
        }

    }.style {
        view ->
        when (view) {
            is TextView -> {
                view.padding = dip(5)
            }
            else -> {
            }
        }
    }.lparams {
        height = dip(50)
    }

    fragmentContainer = frameLayout {
        id = ID_FRAMELAYOUT
    }.lparams {
        below(ID_TOP_BAR)
        above(ID_TABBAR_DIVIDER)
    }
}
```
**some code is removed**, cuz it's not that important. id like `ID_FRAMELAYOUT` is defined in activity as constants.
the var `fragmentContainer`'s layout params indicates `ID_TOP_BAR` layout with this id is customized action bar, 
and `ID_TABBAR_DIVIDER` layout with this id is divider between fragment and tabbar. customized tab bar is below it, whose 
id is `ID_BOTTOM_TAB_BAR`.

and used a way to extend Anko's view manager:
```kotlin
    public inline fun ViewManager.weightTextView() = weightTextView {}
    public inline fun ViewManager.weightTextView(init: WeightTextView.() -> Unit) = ankoView({ WeightTextView(it) }, init)
```
this is a Kotlin language lambda expression. you can custom your own view but if you want to use this view in the `verticalLayout`
expression, you have to use code like that to add your view into the system.

###fragments
`HomeListFragment`, home tab content is displayed in this fragment. 

write layout in fragment is different from activity. in activity, you can do this `verticalLayout{}` in method `onCreate()`.
but in fragment, it's not ok. you have to layout like this `val view = with(ctx){verticalLayout{}}`

in this fragment, a list view will be used to display diaries. list view is very easy as text view or button.
```kotlin
var view = with(ctx) {
    verticalLayout {
        backgroundColor = Color.WHITE

        listView = listView {
            adapter = ArrayAdapter<DiaryModel>(ctx,
                    android.R.layout.simple_list_item_1,
                    AnkoApplication.diaryDataSource)

            onItemClick { adapterView, view, i, l ->
                toast("clicked index: $i, content: ${AnkoApplication.diaryDataSource[i].toString()}")
            }
        }

        emptyTextView = textView {
            text = resources.getString(R.string.list_view_empty)
            textSize = 30f
            gravity = Gravity.CENTER
        }.lparams {
            width = matchParent
            height = matchParent
        }
    }
}

listView?.emptyView = emptyTextView
```
lambda expressin in Kotlin significantly reduced code. just directly add adapter and `onItemClick` listener.

`DetailFragment`, well let's just ignore this one. there's nothing related Anko, just `Fragment` thing.

`DiarySettingsFragment` shows how to use seperated Anko layout.
<br/>first the layout.
```kotlin
class SettingsUI<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        verticalLayout {
            backgroundColor = ContextCompat.getColor(ctx, R.color.SnowWhite)
            textView { text = resources.getString(R.string.settings_title) }

            button("activity with the same `AnkoComponent`") {
                id = ID_BUTTON
            }
        }
    }

    companion object Factory {
        public val ID_BUTTON = 101
    }
}
```
a super easy one, with only a text view and a button in. id of the button is `ID_BUTTON` and it's static.
<br/>how to use it in a fragment.
```kotlin
val view = SettingsUI<DiarySettingsFragment>().createView(AnkoContext.create(ctx, DiarySettingsFragment()))

val button = view.find<Button>(SettingsUI.ID_BUTTON)
button.onClick {
    val i = Intent(activity, TempActivity::class.java)
    startActivity(i)
}
```

  NOTE: you can not add `startActivity()` method in seperated layout. it may cause some error.
  
how to use this seperated layout in a activity.
```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsUI<TempActivity>().setContentView(this)

        val button = find<Button>(SettingsUI.ID_BUTTON)
        button.text = "you are in `TempActivity`, CLICK!"

        button.onClick {
            toast("${TempActivity::class.java.simpleName}")
        }
    }
```
easier than in a fragment.

you can preview Anko layout with a android studio plugin. it's cool!

## More?
ya, you can check out [here](https://github.com/Kotlin/anko/blob/master/doc/ADVANCED.md). no matter what, Anko is just 0.8.x.
it has a long way to go. but it already shows how cool it is.




