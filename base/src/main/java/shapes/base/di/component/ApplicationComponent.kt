package shapes.base.di.component

import android.content.Context
import dagger.Component
import shapes.base.data.Stack
import shapes.base.database.ShapesDao
import shapes.base.di.ApplicationContext
import shapes.base.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun shapesDao(): ShapesDao
    fun stack(): Stack
}
