package shapes.feature.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shapes.base.di.ViewModelKey
import shapes.feature.presentation.StatisticsViewModel

@Module
abstract class StatisticsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StatisticsViewModel::class)
    abstract fun bindViewModel(myViewModel: StatisticsViewModel): ViewModel
}