package shapes.base.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import shapes.base.di.ApplicationContext
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context = application.applicationContext
}
