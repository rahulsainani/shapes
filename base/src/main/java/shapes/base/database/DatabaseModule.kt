package shapes.base.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import shapes.base.di.ApplicationContext

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
