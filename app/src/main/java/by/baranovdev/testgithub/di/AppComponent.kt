package by.baranovdev.testgithub.di

import by.baranovdev.testgithub.MainActivity
import by.baranovdev.testgithub.application.TestApplication
import by.baranovdev.testgithub.presentation.browser.BrowserFragment
import by.baranovdev.testgithub.presentation.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, DomainModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }

    fun inject(application: TestApplication)
    fun inject(activity: MainActivity)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: BrowserFragment)
}