package shapes.feature.di.editor

import dagger.Component
import shapes.base.di.PerActivity
import shapes.base.di.component.ApplicationComponent
import shapes.base.di.module.ViewModelFactoryModule
import shapes.feature.presentation.editor.ShapesEditorActivity

@PerActivity
@Component(
    modules = [ShapesModule::class, ViewModelFactoryModule::class, ShapesViewModelModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ShapesComponent {
    fun inject(activity: ShapesEditorActivity)
}
