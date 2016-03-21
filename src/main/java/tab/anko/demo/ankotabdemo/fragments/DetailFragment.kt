package tab.anko.demo.ankotabdemo.fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.withArguments
import tab.anko.demo.ankotabdemo.AnkoApplication
import tab.anko.demo.ankotabdemo.BaseActivity
import tab.anko.demo.ankotabdemo.R
import tab.anko.demo.ankotabdemo.models.DiaryModel
import tab.anko.demo.ankotabdemo.utils.currentDateString
import tab.anko.demo.ankotabdemo.views.DatePickerFragment
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    var diaryModel: DiaryModel? = null
    var titleEditText: EditText? = null
    var contentEditText: EditText? = null

    public var modelChangeListener: OnModelChanged? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments == null) {
            diaryModel = DiaryModel(title = "", content = "", date = Date())
            return
        }

        diaryModel = arguments.getSerializable(MODEL_INFO) as DiaryModel
        if (diaryModel == null) {
            diaryModel = DiaryModel(title = "", content = "", date = Date())
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var baseAct = act as BaseActivity
        baseAct.customTitle = "Write a diary"

        return with(ctx) {
            verticalLayout {
                padding = dip(10)
                backgroundColor = Color.WHITE
                textView("TITLE") {

                }.lparams(width = matchParent)

                titleEditText = editText {
                    hint = currentDateString()
                    lines = 1
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }

                textView("CONTENT") {

                }.lparams(width = matchParent) {
                    topMargin = dip(15)
                }

                contentEditText = editText {
                    hint = "what's going on..."
                    setHorizontallyScrolling(false)
                }.lparams(width = matchParent) {
                    //                    height = matchParent
                    topMargin = dip(5)
                }

                button(R.string.button_select_time) {
                    gravity = Gravity.CENTER
                    onClick {
                        val fm = activity.supportFragmentManager
                        var datePicker = DatePickerFragment.newInstance(diaryModel?.date)
                        datePicker.setTargetFragment(this@DetailFragment, DetailFragment.REQUEST_DATE)
                        datePicker.show(fm, "date")
                    }
                }

                button(R.string.button_detail_ok) {
                    onClick {
                        v ->
                        println("ok button clicked")
                        try {
                            var model = diaryModel!!
                            model.title = titleEditText?.text.toString()
                            model.content = contentEditText?.text.toString()
                            AnkoApplication.diaryDataSource.add(model)

                            modelChangeListener?.modelChanged()

                            toast(R.string.model_saved_ok)
                        } catch(e: Exception) {
                            Log.d("##DetailFragment", "error: ${e.toString()}")
                            toast(R.string.model_save_error)
                        }
                    }
                }.lparams {
                    topMargin = dip(10)
                    width = matchParent
                }
            }.style {
                view ->
                when (view) {
                    is Button -> {
                        view.gravity = Gravity.CENTER
                    }
                    is TextView -> {
                        view.gravity = Gravity.LEFT
                        view.textSize = 20f
                        view.textColor = Color.DKGRAY
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode != REQUEST_DATE) {
            return
        }

        var date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
        diaryModel?.date = date
    }

    companion object Factory {
        val REQUEST_DATE = 0

        val MODEL_INFO = "model_info"

        fun newInstance(model: DiaryModel?): DetailFragment {

            //            var args = Bundle()
            //            args.putString("title", title)
            //            args.putString("content", content)
            //
            //            val fragment = DetailFragment()
            //            fragment.arguments = args
            //            return fragment

            var fragment: DetailFragment? = null
            if (model == null) {
                fragment = DetailFragment()
            } else {
                fragment = DetailFragment().withArguments(MODEL_INFO to model!!)
            }

            return fragment!!
        }
    }

    class MiddleButton(context: Context) : Button(context) {
        init {

            this.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            this.gravity = Gravity.CENTER
        }
    }

    public inline fun ViewManager.middleButton() = middleButton {}
    public inline fun ViewManager.middleButton(init: MiddleButton.() -> Unit) = ankoView({ MiddleButton(it) }, init)

    interface OnModelChanged {
        fun modelChanged()
    }
}
