package tab.anko.demo.ankotabdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    val items = listOf("tabs 1", "tabs 2", "tabs 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(16)
            val list = listView() {
                adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, items)
                onItemClickListener = object : AdapterView.OnItemClickListener {
                    override fun onItemClick(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                        when (position) {
                            0 -> {
                                startActivity<TabDemo1>()
                            }
                            1 -> {
                                toast("${MainActivity::class.java}")
                            }
                        }
                    }
                }
            }.lparams(width = matchParent) {
                height = matchParent
            }
        }
    }
}
