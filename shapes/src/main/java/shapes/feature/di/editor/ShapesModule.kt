package shapes.feature.di.editor

import dagger.Module
import dagger.Provides
import shapes.feature.data.ShapesRepository
import shapes.feature.domain.IShapesRepository

@Module
class ShapesModule {

    @Provides
    fun provideShapesRepository(shapesRepository: ShapesRepository): IShapesRepository =
        shapesRepository
}
