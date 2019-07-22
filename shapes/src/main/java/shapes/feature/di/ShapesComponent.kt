package shapes.feature.di

import dagger.Component
import shapes.base.di.PerActivity
import shapes.base.di.component.ApplicationComponent
import shapes.base.di.module.ViewModelFactoryModule
import shapes.feature.presentation.ShapesEditorActivity

@PerActivity
@Component(
    modules = [ShapesModule::class, ViewModelFactoryModule::class, ViewModelModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ShapesComponent {
    fun inject(activity: ShapesEditorActivity)
}
