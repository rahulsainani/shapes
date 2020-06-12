package app.droidster.shapes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class ShapesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        plantTimber()
    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
