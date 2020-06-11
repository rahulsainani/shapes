package shapes.feature.di.editor

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import shapes.feature.data.ShapesRepository
import shapes.feature.domain.IShapesRepository

@InstallIn(ActivityComponent::class)
@Module
object ShapesModule {

    @Provides
    @Reusable
    fun provideShapesRepository(shapesRepository: ShapesRepository): IShapesRepository =
        shapesRepository
}
