package shapes.base.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import shapes.base.di.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
