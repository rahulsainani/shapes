package shapes.base.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import shapes.base.data.ShapeDataStack
import shapes.base.di.ApplicationContext
import shapes.base.di.module.DatabaseModule
import shapes.database.ShapesDao

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
