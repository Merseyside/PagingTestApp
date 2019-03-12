package com.merseyside.pagingtestapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.merseyside.pagingtestapp.presentation.di.component.AppComponent
import com.merseyside.pagingtestapp.presentation.di.component.DaggerAppComponent
import com.merseyside.pagingtestapp.presentation.di.module.AppModule

class PropertyApplication : Application() {

    companion object {
        lateinit var application : PropertyApplication

        fun getInstance() : PropertyApplication {
            return application
        }
    }

    lateinit var appComponent : AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        application = this
        appComponent = buildComponent()
        appComponent.inject(this)
    }

    private fun buildComponent() : AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}