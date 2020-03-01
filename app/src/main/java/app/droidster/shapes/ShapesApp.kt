package app.droidster.shapes

import android.app.Application
import shapes.di.AppComponentProvider
import shapes.di.component.ApplicationComponent
import shapes.di.component.DaggerApplicationComponent
import timber.log.Timber

class ShapesApp : Application(), AppComponentProvider {

    private val applicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationContext(this)
            .build()
    }

    override fun provideAppComponent(): ApplicationComponent = applicationComponent

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
