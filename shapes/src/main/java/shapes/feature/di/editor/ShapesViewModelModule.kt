package shapes.feature.di.editor

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shapes.di.ViewModelKey
import shapes.feature.presentation.editor.ShapesEditorViewModel

@Module
abstract class ShapesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ShapesEditorViewModel::class)
    abstract fun bindViewModel(myViewModel: ShapesEditorViewModel): ViewModel
}
