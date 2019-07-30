package shapes.base.di.component

import android.content.Context
import dagger.Component
import javax.inject.Singleton
import shapes.base.data.Stack
import shapes.base.database.ShapesDao
import shapes.base.di.ApplicationContext
import shapes.base.di.module.ApplicationModule
import shapes.base.rx.SchedulerProvider

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun shapesDao(): ShapesDao
    fun stack(): Stack
    fun schedulerProvider(): SchedulerProvider
}
