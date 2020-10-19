package ru.farkhodkhaknazarov.pickabureader.di.component

import dagger.Component
import ru.farkhodkhaknazarov.pickabureader.di.api.ApplicationApi
import ru.farkhodkhaknazarov.pickabureader.di.module.PikabuModule
import ru.farkhodkhaknazarov.pickabureader.di.scope.PerPikabu
import ru.farkhodkhaknazarov.pickabureader.ui.presenter.PostsFragmentPresenter
import ru.farkhodkhaknazarov.pickabureader.ui.presenter.SavedPostsFragmentPresenter

@PerPikabu
@Component(
    modules = [PikabuModule::class], dependencies = [ApplicationApi::class]
)
interface PikabuComponent {
    companion object {
        private var component: PikabuComponent? = null

        fun init() {
            component = DaggerPikabuComponent.builder()
                .pikabuModule(PikabuModule())
                .applicationApi(AppComponent.get())
                .build()
        }

        fun get(): PikabuComponent =
            component
                ?: throw IllegalStateException("Component is not initialized. Call `init` method first.")
    }

    fun inject(postsFragmentPresenter: PostsFragmentPresenter)
    fun inject(postsFragmentPresenter: SavedPostsFragmentPresenter)
}