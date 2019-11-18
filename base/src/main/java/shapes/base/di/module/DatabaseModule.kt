package shapes.base.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import shapes.base.di.ApplicationContext
import shapes.database.AppDatabase
import shapes.database.ShapesDao
import shapes.database.ShapesDao_Impl

@Module
object DatabaseModule {

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
