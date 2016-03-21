package tab.anko.demo.ankotabdemo.models

import java.io.Serializable
import java.util.*

/**
 * Created by uncle_charlie on 16/3/2016.
 */
data class DiaryModel(var title: String, var content: String, var date: Date) : Serializable {
}