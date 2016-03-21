package tab.anko.demo.ankotabdemo.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

/**
 * A simple [Fragment] subclass.
 */
class TabFragment() : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                textView("Fragment Text View") {}
            }
        }.view
    }

    //    companion object {
    //        set
    //    }
}
