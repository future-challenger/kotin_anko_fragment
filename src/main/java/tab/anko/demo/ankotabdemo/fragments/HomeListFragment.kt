package tab.anko.demo.ankotabdemo.fragments


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import tab.anko.demo.ankotabdemo.AnkoApplication
import tab.anko.demo.ankotabdemo.BaseActivity
import tab.anko.demo.ankotabdemo.R
import tab.anko.demo.ankotabdemo.models.DiaryModel

/**
 * A simple [Fragment] subclass.
 */
class HomeListFragment() : Fragment(), DetailFragment.OnModelChanged {

    var listView: ListView? = null
    var emptyTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var baseAct = act as BaseActivity
        baseAct.customTitle = "Diaries"

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

        return view
    }

    override fun onResume() {
        super.onResume()
        var arrayAdapter = listView?.adapter as ArrayAdapter<DiaryModel>
        arrayAdapter?.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun modelChanged() {
        var arrayAdapter = listView?.adapter as ArrayAdapter<DiaryModel>
        arrayAdapter?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): HomeListFragment {
            return HomeListFragment()
        }
    }
}
