package ru.farkhodkhaknazarov.pickabureader.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.farkhodkhaknazarov.pickabureader.ui.adapter.PostsAdapter
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem

@AddToEndSingle
interface BaseInterface: MvpView {
    fun initView() {}
    fun updateList(postList: List<PostItem>) {}
    fun notifyPostUpdated(item: PostItem)
}