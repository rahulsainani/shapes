package shapes.feature.di.stats

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import shapes.base.di.ViewModelKey
import shapes.feature.presentation.stats.StatisticsViewModel

@InstallIn(ActivityComponent::class)
@Module
abstract class StatisticsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StatisticsViewModel::class)
    abstract fun bindViewModel(myViewModel: StatisticsViewModel): ViewModel
}
