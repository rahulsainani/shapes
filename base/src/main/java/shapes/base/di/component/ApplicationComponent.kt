package shapes.base.di.component

import android.content.Context
import dagger.Component
import shapes.base.data.ShapeDataStack
import shapes.base.database.ShapesDao
import shapes.base.di.ApplicationContext
import shapes.base.di.module.ApplicationModule
import shapes.base.rx.SchedulerProvider
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun shapesDao(): ShapesDao
    fun shapeDataStack(): ShapeDataStack
    fun schedulerProvider(): SchedulerProvider
}
