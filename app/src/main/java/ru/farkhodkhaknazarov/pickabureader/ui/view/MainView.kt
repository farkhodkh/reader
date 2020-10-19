package ru.farkhodkhaknazarov.pickabureader.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.farkhodkhaknazarov.pickabureader.ui.adapter.FragmentAdapter

@AddToEndSingle
interface MainView : MvpView {
    @AddToEndSingle
    fun setupMainAdapter()

    @AddToEndSingle
    fun setupPager(adapter: FragmentAdapter)
}