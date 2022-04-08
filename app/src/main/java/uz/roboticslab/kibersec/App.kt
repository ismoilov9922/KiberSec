package uz.roboticslab.kibersec

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import uz.roboticslab.kibersec.utils.Settings

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        if (getSharedPreferences(
                Settings.PREF_NAME,
                Context.MODE_PRIVATE
            ).getBoolean(Settings.NIGHT_MODE_KEY, false)
        ) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}