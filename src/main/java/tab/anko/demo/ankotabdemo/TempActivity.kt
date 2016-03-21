package tab.anko.demo.ankotabdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import tab.anko.demo.ankotabdemo.fragments.SettingsUI

class TempActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsUI<TempActivity>().setContentView(this)

        val button = find<Button>(SettingsUI.ID_BUTTON)
        button.text = "you are in `TempActivity`, CLICK!"

        button.onClick {
            toast("${TempActivity::class.java.simpleName}")
        }
    }
}
