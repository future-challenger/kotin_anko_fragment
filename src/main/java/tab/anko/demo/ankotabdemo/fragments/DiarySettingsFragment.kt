package tab.anko.demo.ankotabdemo.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.ctx
import tab.anko.demo.ankotabdemo.TempActivity

/**
 * A simple [Fragment] subclass.
 */
class DiarySettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //                val textView = TextView(activity)
        //                textView.text = "settings"
        //                return textView
        val view = SettingsUI<DiarySettingsFragment>().createView(AnkoContext.create(ctx, DiarySettingsFragment()))

        val button = view.find<Button>(SettingsUI.ID_BUTTON)
        button.onClick {
            val i = Intent(activity, TempActivity::class.java)
            startActivity(i)
        }

        return view
    }

    companion object {

        fun newInstance(): DiarySettingsFragment {
            return DiarySettingsFragment()
        }
    }
}// Required empty public constructor
