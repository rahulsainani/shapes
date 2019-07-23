package shapes.feature.di

import dagger.Component
import shapes.base.di.PerActivity
import shapes.base.di.component.ApplicationComponent
import shapes.base.di.module.ViewModelFactoryModule
import shapes.feature.presentation.StatisticsActivity

@PerActivity
@Component(
    modules = [StatisticsModule::class, ViewModelFactoryModule::class, StatisticsViewModelModule::class],
    dependencies = [ApplicationComponent::class]
)
interface StatisticsComponent {
    fun inject(activity: StatisticsActivity)
}
