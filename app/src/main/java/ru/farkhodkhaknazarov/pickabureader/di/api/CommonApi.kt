package ru.farkhodkhaknazarov.pickabureader.di.api

import android.app.Activity
import moxy.MvpPresenter
import ru.farkhodkhaknazarov.pickabureader.ui.adapter.PostsAdapter
import ru.farkhodkhaknazarov.pickabureader.ui.fragment.PostsFragment
import ru.farkhodkhaknazarov.pickabureader.ui.fragment.SavedFragment
import ru.farkhodkhaknazarov.pickabureader.ui.presenter.MainActivityPresenter

interface CommonApi {
    fun inject(activity: Activity)
    fun inject(presenter: MainActivityPresenter)
    fun inject(adapter: PostsAdapter)
    fun getMainActivityPresenter(): MainActivityPresenter
    fun getPostsFragment(): PostsFragment
    fun getSavedFragment(): SavedFragment
}