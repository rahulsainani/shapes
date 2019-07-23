package shapes.feature.di

import dagger.Module
import dagger.Provides
import shapes.feature.data.ShapesRepository
import shapes.feature.domain.IShapesRepository

@Module
class StatisticsModule {

    @Provides
    fun provideShapesRepository(shapesRepository: ShapesRepository): IShapesRepository =
        shapesRepository
}
