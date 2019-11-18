package shapes.feature.di.editor

import dagger.Module
import dagger.Provides
import dagger.Reusable
import shapes.feature.data.ShapesRepository
import shapes.feature.domain.IShapesRepository

@Module
object ShapesModule {

    @Provides
    @Reusable
    fun provideShapesRepository(shapesRepository: ShapesRepository): IShapesRepository =
        shapesRepository
}
