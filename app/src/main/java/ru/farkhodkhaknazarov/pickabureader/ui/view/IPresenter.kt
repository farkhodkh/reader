package ru.farkhodkhaknazarov.pickabureader.ui.view

import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem

interface IPresenter {
    fun updatePostList(postList: List<PostItem>) {}
    fun onClose()
}