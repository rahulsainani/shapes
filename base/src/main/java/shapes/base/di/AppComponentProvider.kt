package shapes.base.di

import shapes.base.di.component.ApplicationComponent

interface AppComponentProvider {
    fun provideAppComponent(): ApplicationComponent
}
