package by.baranovdev.testgithub.application

import android.app.Application
import by.baranovdev.testgithub.di.AppComponent
import by.baranovdev.testgithub.di.AppModule
import by.baranovdev.testgithub.di.DaggerAppComponent

class TestApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(AppModule(this))
    }

}