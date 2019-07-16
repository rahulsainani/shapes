package app.droidster.shapes

import android.app.Application
import shapes.base.di.AppComponentProvider
import shapes.base.di.component.ApplicationComponent
import shapes.base.di.component.DaggerApplicationComponent
import shapes.base.di.module.ApplicationModule
import timber.log.Timber

class ShapesApp : Application(), AppComponentProvider {

    private val applicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
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