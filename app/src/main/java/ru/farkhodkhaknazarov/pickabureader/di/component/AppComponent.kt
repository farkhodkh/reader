package ru.farkhodkhaknazarov.pickabureader.di.component

import android.content.Context
import dagger.Component
import ru.farkhodkhaknazarov.pickabureader.di.api.ApplicationApi
import ru.farkhodkhaknazarov.pickabureader.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent: ApplicationApi {
    companion object {
        private var component: AppComponent? = null

        fun init(context: Context) {
            component = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
        }

        fun get(): AppComponent =
            component
                ?: throw IllegalStateException("Component is not initialized. Call `init` method first.")
    }
}