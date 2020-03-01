package shapes.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import shapes.base.di.ApplicationContext
import shapes.database.ShapesDao
import shapes.database.data.ShapeDataStack
import shapes.di.module.DatabaseModule

@Singleton
@Component(modules = [DatabaseModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun shapesDao(): ShapesDao
    fun shapeDataStack(): ShapeDataStack

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(@ApplicationContext applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }
}
