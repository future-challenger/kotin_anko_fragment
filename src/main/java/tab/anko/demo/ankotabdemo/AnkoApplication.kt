package tab.anko.demo.ankotabdemo

import android.app.Application
import tab.anko.demo.ankotabdemo.models.DiaryModel

/**
 * Created by uncle_charlie on 16/3/2016.
 */
class AnkoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        var diaryDataSource = mutableListOf<DiaryModel>()
    }
}
