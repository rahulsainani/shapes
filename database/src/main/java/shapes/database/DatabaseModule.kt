package shapes.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
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
