package shapes.base.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import shapes.base.database.AppDatabase
import shapes.base.database.ShapesDao
import shapes.base.database.ShapesDao_Impl
import shapes.base.di.ApplicationContext

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, "shapes-database")
            .build()

    @Singleton
    @Provides
    fun provideShapesDao(appDatabase: AppDatabase): ShapesDao =
        ShapesDao_Impl(appDatabase)
}
