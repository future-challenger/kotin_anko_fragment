package tab.anko.demo.ankotabdemo.views


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.withArguments
import tab.anko.demo.ankotabdemo.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DatePickerFragment : DialogFragment() {

    var mDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mDate = arguments.getSerializable(EXTRA_DATE) as Date
            if (mDate == null) {
                mDate = Date()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return this.view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        calendar.time = mDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        var pickerView = DatePicker(activity)
        pickerView.calendarViewShown = false
        pickerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        pickerView.init(year, month, day) {
            view, year, month, day ->
            mDate = GregorianCalendar(year, month, day).time

            arguments.putSerializable(EXTRA_DATE, mDate)
        }

        return AlertDialog.Builder(activity)
                .setView(pickerView)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(R.string.picker_button_ok) { dialog, which ->
                    toast("hello world!")
                    sendResult(Activity.RESULT_OK)
                }.create()
    }

    private fun sendResult(resultCode: Int) {
        if (targetFragment == null)
            return

        var i = Intent()
        i.putExtra(EXTRA_DATE, mDate)
        targetFragment.onActivityResult(targetRequestCode, resultCode, i)
    }

    companion object {
        val EXTRA_DATE = "extra_date"

        fun newInstance(date: Date?): DatePickerFragment {
            var d = date ?: Date()
            return DatePickerFragment().withArguments(EXTRA_DATE to d)
        }
    }

}
