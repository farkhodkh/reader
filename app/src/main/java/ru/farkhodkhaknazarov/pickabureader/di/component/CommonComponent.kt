package ru.farkhodkhaknazarov.pickabureader.di.component

import dagger.Component
import ru.farkhodkhaknazarov.pickabureader.di.api.ApplicationApi
import ru.farkhodkhaknazarov.pickabureader.di.api.CommonApi
import ru.farkhodkhaknazarov.pickabureader.di.module.CommonModule
import ru.farkhodkhaknazarov.pickabureader.di.scope.PerApplication

@PerApplication
@Component(modules = [CommonModule::class], dependencies = [ApplicationApi::class])
interface CommonComponent : CommonApi {
    companion object {
        private var component: CommonComponent? = null

        fun init() {
            component = DaggerCommonComponent.builder()
                .commonModule(CommonModule())
                .applicationApi(AppComponent.get())
                .build()
        }

        fun get(): CommonComponent =
            component
                ?: throw IllegalStateException("Component is not initialized. Call `init` method first.")
    }
}