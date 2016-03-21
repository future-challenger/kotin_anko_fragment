package tab.anko.demo.ankotabdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    open var customTitle: String? = null
        get() = field
        set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        relativeLayout {
        //            textView {
        //                text = "base text view"
        //            }
        //        }
    }


}
