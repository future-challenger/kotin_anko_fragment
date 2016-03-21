package tab.anko.demo.ankotabdemo.fragments

import android.support.v4.content.ContextCompat
import org.jetbrains.anko.*
import tab.anko.demo.ankotabdemo.R

/**
 * Created by uncle_charlie on 20/3/16.
 */
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
