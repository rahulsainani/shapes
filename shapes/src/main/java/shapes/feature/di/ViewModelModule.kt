package shapes.feature.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shapes.base.di.ViewModelKey
import shapes.feature.presentation.ShapesEditorViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ShapesEditorViewModel::class)
    abstract fun bindViewModel(myViewModel: ShapesEditorViewModel): ViewModel
}