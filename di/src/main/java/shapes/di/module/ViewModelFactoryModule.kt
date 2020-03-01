package shapes.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import shapes.di.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
