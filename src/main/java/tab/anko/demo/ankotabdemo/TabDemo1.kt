package tab.anko.demo.ankotabdemo

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.ViewManager
import android.view.Window
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import tab.anko.demo.ankotabdemo.fragments.DetailFragment
import tab.anko.demo.ankotabdemo.fragments.DiarySettingsFragment
import tab.anko.demo.ankotabdemo.fragments.HomeListFragment

class TabDemo1 : BaseActivity() {

    val ID_RELATIVELAYOUT = 123
    val ID_FRAMELAYOUT: Int = 125
    val ID_TOP_BAR = 124
    val ID_BOTTOM_TAB_BAR = 126
    val ID_TABBAR_DIVIDER = 127

    var titleTextView: TextView? = null

    var fragmentContainer: FrameLayout? = null

    var homeListFragment: HomeListFragment? = null
    var detailFragment: DetailFragment? = null
    var settingsFragment: DiarySettingsFragment? = null

    var homeListTab: TextView? = null
    var detailTab: TextView? = null
    var settingsTab: TextView? = null

    override var customTitle: String? = null
        set(value) {
            field = value
            if (titleTextView != null) {
                titleTextView?.text = value
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        relativeLayout {
            id = ID_RELATIVELAYOUT

            backgroundColor = Color.LTGRAY

            linearLayout {
                id = ID_TOP_BAR
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                orientation = LinearLayout.HORIZONTAL

                titleTextView = textView {
                    text = "Some Title"
                    textSize = 16f
                    textColor = Color.WHITE
                    //                    backgroundColor = Color.CYAN
                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
                }.lparams {
                    width = dip(0)
                    height = matchParent
                    weight = 1f
                }
            }.lparams {
                width = matchParent
                height = dip(50)
                alignParentTop()
            }

            view {
                id = ID_TABBAR_DIVIDER
                backgroundColor = Color.GRAY
            }.lparams {
                width = matchParent
                height = 1
                above(ID_BOTTOM_TAB_BAR)
            }

            linearLayout {
                id = ID_BOTTOM_TAB_BAR
                orientation = LinearLayout.HORIZONTAL
                backgroundColor = Color.WHITE

                homeListTab = weightTextView {
                    text = "List"
                    normalDrawable = resources.getDrawable(R.mipmap.tab_my_normal)
                    selectedDrawable = resources.getDrawable(R.mipmap.tab_my_pressed)
                    onClick { tabClick(0) }
                }

                detailTab = weightTextView {
                    text = "Detail"
                    normalDrawable = resources.getDrawable(R.mipmap.tab_channel_normal)
                    selectedDrawable = resources.getDrawable(R.mipmap.tab_channel_pressed)
                    onClick { tabClick(1) }
                }

                settingsTab = weightTextView {
                    text = "Settings"
                    normalDrawable = resources.getDrawable(R.mipmap.tab_better_normal)
                    selectedDrawable = resources.getDrawable(R.mipmap.tab_better_pressed)
                    onClick { tabClick(2) }
                }

            }.style {
                view ->
                when (view) {
                    is TextView -> {
                        view.padding = dip(5)
                        view.compoundDrawablePadding = dip(3)
                        view.textSize = 10f
                        view.gravity = Gravity.CENTER
                    }
                    else -> {
                    }
                }
            }.lparams {
                height = dip(50)
                width = matchParent
                alignParentBottom()
            }

            fragmentContainer = frameLayout {
                id = ID_FRAMELAYOUT
                backgroundColor = Color.GREEN
            }.lparams {
                below(ID_TOP_BAR)
                above(ID_TABBAR_DIVIDER)
                width = matchParent
                height = matchParent
            }
        }

        //        var fl = find<FrameLayout>(ID_FRAMELAYOUT)
        //        if (fragmentContainer != null) {
        //            Log.d("Tab1", "find framelayout")
        //        } else {
        //            Log.d("Tab1", "not find framelayout")
        //        }

        prepareTabFragments()
        tabClick(0)
    }

    fun tabClick(index: Int) {
        //        toast("index is $index")
        //        Log.d("", "index is $index")
        info("index is $index")
        val ft = supportFragmentManager.beginTransaction()
        ft.hide(homeListFragment)
        ft.hide(detailFragment)
        ft.hide(settingsFragment)

        // unselect all textviews
        homeListTab?.isSelected = false
        detailTab?.isSelected = false
        settingsTab?.isSelected = false

        when (index) {
            0 -> {
                homeListTab?.isSelected = true
                ft.show(homeListFragment)
            }
            1 -> {
                detailTab?.isSelected = true
                ft.show(detailFragment)
            }
            2 -> {
                settingsTab?.isSelected = true
                ft.show(settingsFragment)
            }
            else -> {

            }
        }

        ft.commit()
    }

    fun prepareTabFragments() {
        val fm = supportFragmentManager
        homeListFragment = HomeListFragment.newInstance()
        fm.beginTransaction()
                .add(ID_FRAMELAYOUT, homeListFragment)
                .commit()
        detailFragment = DetailFragment.newInstance(null)
        detailFragment?.modelChangeListener = homeListFragment
        fm.beginTransaction()
                .add(ID_FRAMELAYOUT, detailFragment)
                .commit()
        settingsFragment = DiarySettingsFragment.newInstance()
        fm.beginTransaction()
                .add(ID_FRAMELAYOUT, settingsFragment)
                .commit()
    }

    class WeightTextView(context: Context) : TextView(context) {
        var normalDrawable: Drawable? = null
        var selectedDrawable: Drawable? = null

        init {
            var layoutParams = LinearLayout.LayoutParams(dip(50),
                    LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            layoutParams.weight = 1f
            this.layoutParams = layoutParams
        }

        override fun setSelected(selected: Boolean) {
            super.setSelected(selected)

            if (selected) {
                this.backgroundColor = ContextCompat.getColor(context, R.color.textGray)
                this.textColor = ContextCompat.getColor(context, R.color.textYellow)

                if (selectedDrawable != null) {
                    this.setCompoundDrawablesWithIntrinsicBounds(null, selectedDrawable, null, null)
                }
            } else {
                this.backgroundColor = ContextCompat.getColor(context, android.R.color.transparent)
                this.textColor = ContextCompat.getColor(context, R.color.textGray)
                if (normalDrawable != null) {
                    this.setCompoundDrawablesWithIntrinsicBounds(null, normalDrawable, null, null)
                }
            }
        }
    }

    public inline fun ViewManager.weightTextView() = weightTextView {}
    public inline fun ViewManager.weightTextView(init: WeightTextView.() -> Unit) = ankoView({ WeightTextView(it) }, init)

    public inline fun AppCompatActivity.info(message: Any?) {
        Log.i(this.localClassName, message.toString())
    }
}
