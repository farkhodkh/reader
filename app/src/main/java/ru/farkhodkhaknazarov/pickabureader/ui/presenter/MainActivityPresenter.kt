package ru.farkhodkhaknazarov.pickabureader.ui.presenter

import moxy.MvpPresenter
import ru.farkhodkhaknazarov.pickabureader.di.component.CommonComponent
import ru.farkhodkhaknazarov.pickabureader.ui.adapter.FragmentAdapter
import ru.farkhodkhaknazarov.pickabureader.ui.fragment.PostsFragment
import ru.farkhodkhaknazarov.pickabureader.ui.fragment.SavedFragment
import ru.farkhodkhaknazarov.pickabureader.ui.view.MainView
import javax.inject.Inject

class MainActivityPresenter : MvpPresenter<MainView>() {
    lateinit var adapter: FragmentAdapter

    @Inject
    lateinit var listFragment: PostsFragment

    @Inject
    lateinit var savedFragment: SavedFragment

    override fun onFirstViewAttach() {
        CommonComponent.init()
        CommonComponent.get().inject(this)

        viewState.setupMainAdapter()
    }


    fun prepareAdapter(adapter: FragmentAdapter) {
        adapter.titels.add("All")
        adapter.titels.add("Saved")

        adapter.fragments.add(listFragment)
        adapter.fragments.add(savedFragment)

        viewState.setupPager(adapter)
    }
}