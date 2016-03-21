package tab.anko.demo.ankotabdemo.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by uncle_charlie on 16/3/2016.
 */

/**
 * @return a date string, converted from current date.
 */
fun currentDateString(targetDate: Date? = null): String {
    val df = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
    val dateString = df.format(targetDate ?: Calendar.getInstance().getTime());
    return dateString
}