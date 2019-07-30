package shapes.feature.di.stats

import dagger.Component
import shapes.base.di.PerActivity
import shapes.base.di.component.ApplicationComponent
import shapes.base.di.module.ViewModelFactoryModule
import shapes.feature.di.editor.ShapesModule
import shapes.feature.presentation.stats.StatisticsActivity

@PerActivity
@Component(
    modules = [
        ShapesModule::class,
        ViewModelFactoryModule::class,
        StatisticsViewModelModule::class
    ],
    dependencies = [ApplicationComponent::class]
)
interface StatisticsComponent {
    fun inject(activity: StatisticsActivity)
}
