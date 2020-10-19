package ru.farkhodkhaknazarov.pickabureader.di.module

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import ru.farkhodkhaknazarov.pickabureader.di.scope.PerApplication
import ru.farkhodkhaknazarov.pickabureader.ui.fragment.PostsFragment
import ru.farkhodkhaknazarov.pickabureader.ui.fragment.SavedFragment
import ru.farkhodkhaknazarov.pickabureader.ui.presenter.MainActivityPresenter

@Module
class CommonModule {

    @PerApplication
    @Provides
    fun providesMainActivityPresenter(): MainActivityPresenter = MainActivityPresenter()

    @PerApplication
    @Provides
    fun providesPostsFragment(): PostsFragment = PostsFragment()

    @PerApplication
    @Provides
    fun providesSavedFragment(): SavedFragment = SavedFragment()

    @PerApplication
    @Provides
    fun providesPicasso(context: Context): Picasso = Picasso.with(context)
}