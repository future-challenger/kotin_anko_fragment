//import android.app.Activity
//import android.os.Bundle
//import android.view.Gravity
//import android.widget.LinearLayout
//import org.jetbrains.anko.*
//
//class SomeActivity : Activity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super<Activity>.onCreate(savedInstanceState)
//
//        relativeLayout {
//            relativeLayout {
//                backgroundResource = R.color.bg_topbar
//                id = Ids.ly_top_bar
//
//                textView("信息") {
//                    gravity = Gravity.CENTER
//                    id = Ids.txt_topbar
//                    textColor = R.color.text_topbar
//                    textSize = 18f
//                }.lparams(width = matchParent, height = matchParent) {
//                    centerInParent()
//                }
//                view {
//                    backgroundResource = R.color.div_white
//                }.lparams(width = matchParent, height = px(2)) {
//                    alignParentBottom()
//                }
//            }.lparams(width = matchParent, height = dp(48))
//            linearLayout {
//                backgroundResource = R.color.bg_white
//                id = Ids.ly_tab_bar
//                orientation = LinearLayout.HORIZONTAL
//
//                textView(R.string.tab_menu_alert) {
//                    backgroundResource = R.drawable.tab_menu_bg
//                    drawablePadding = dip(3)
//                    drawableTop = R.drawable.tab_menu_channel
//                    gravity = Gravity.CENTER
//                    id = Ids.txt_channel
//                    padding = dip(5)
//                    textColor = R.drawable.tab_menu_text
//                    textSize = 16f
//                }.lparams(width = dp(0), height = matchParent) {
//                    weight = 1
//                }
//                textView(R.string.tab_menu_profile) {
//                    backgroundResource = R.drawable.tab_menu_bg
//                    drawablePadding = dip(3)
//                    drawableTop = R.drawable.tab_menu_message
//                    gravity = Gravity.CENTER
//                    id = Ids.txt_message
//                    padding = dip(5)
//                    textColor = R.drawable.tab_menu_text
//                    textSize = 16f
//                }.lparams(width = dp(0), height = matchParent) {
//                    weight = 1
//                }
//                textView(R.string.tab_menu_pay) {
//                    backgroundResource = R.drawable.tab_menu_bg
//                    drawablePadding = dip(3)
//                    drawableTop = R.drawable.tab_menu_better
//                    gravity = Gravity.CENTER
//                    id = Ids.txt_better
//                    padding = dip(5)
//                    textColor = R.drawable.tab_menu_text
//                    textSize = 16f
//                }.lparams(width = dp(0), height = matchParent) {
//                    weight = 1
//                }
//                textView(R.string.tab_menu_setting) {
//                    backgroundResource = R.drawable.tab_menu_bg
//                    drawablePadding = dip(3)
//                    drawableTop = R.drawable.tab_menu_setting
//                    gravity = Gravity.CENTER
//                    id = Ids.txt_setting
//                    padding = dip(5)
//                    textColor = R.drawable.tab_menu_text
//                    textSize = 16f
//                }.lparams(width = dp(0), height = matchParent) {
//                    weight = 1
//                }
//            }.lparams(width = matchParent, height = dp(56)) {
//                alignParentBottom()
//            }
//            view {
//                backgroundResource = R.color.div_white
//                id = Ids.div_tab_bar
//            }.lparams(width = matchParent, height = px(2)) {
//                above(R.id.ly_tab_bar)
//            }
//            frameLayout {
//                id = Ids.ly_content
//            }.lparams(width = matchParent, height = matchParent) {
//                above(R.id.div_tab_bar)
//                below(R.id.ly_top_bar)
//            }
//        }
//    }
//
//    private object Ids {
//        val div_tab_bar = 1
//        val ly_content = 2
//        val ly_tab_bar = 3
//        val ly_top_bar = 4
//        val txt_better = 5
//        val txt_channel = 6
//        val txt_message = 7
//        val txt_setting = 8
//        val txt_topbar = 9
//    }
//}