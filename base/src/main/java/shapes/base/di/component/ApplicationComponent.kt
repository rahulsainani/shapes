package shapes.base.di.component

import dagger.Component
import shapes.base.data.Stack
import shapes.base.database.ShapesDao
import shapes.base.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun shapesDao(): ShapesDao
    fun stack(): Stack
}
