package shapes.di

import shapes.di.component.ApplicationComponent

interface AppComponentProvider {
    fun provideAppComponent(): ApplicationComponent
}
