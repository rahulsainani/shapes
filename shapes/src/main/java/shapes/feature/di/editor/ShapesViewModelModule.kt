package shapes.feature.di.editor

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import shapes.base.di.ViewModelKey
import shapes.feature.presentation.editor.ShapesEditorViewModel

@InstallIn(ActivityComponent::class)
@Module
abstract class ShapesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ShapesEditorViewModel::class)
    abstract fun bindViewModel(myViewModel: ShapesEditorViewModel): ViewModel
}
