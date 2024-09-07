package by.baranovdev.testgithub.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

class AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}