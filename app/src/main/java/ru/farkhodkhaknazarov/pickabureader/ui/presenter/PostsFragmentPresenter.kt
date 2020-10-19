package ru.farkhodkhaknazarov.pickabureader.ui.presenter

import moxy.MvpPresenter
import ru.farkhodkhaknazarov.pickabureader.api.PikabuModel
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem
import ru.farkhodkhaknazarov.pickabureader.common.warning
import ru.farkhodkhaknazarov.pickabureader.di.component.PikabuComponent
import ru.farkhodkhaknazarov.pickabureader.ui.view.BaseInterface
import ru.farkhodkhaknazarov.pickabureader.ui.view.IPresenter
import javax.inject.Inject

class PostsFragmentPresenter : MvpPresenter<BaseInterface>(), IPresenter {
    @Inject
    lateinit var module: PikabuModel

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()

        PikabuComponent.init()
        PikabuComponent.get().inject(this)

        module
            .observePostsDataList()
            .subscribe(this::updatePostList) { warning(it) }

        module.getPostsFromNetwork()
    }

    override fun updatePostList(postList: List<PostItem>) {
        viewState.updateList(postList)
    }

    override fun onClose() {
        module.closeModel()
    }

    fun onSaveBtnClick(item: PostItem) {
        module.savePostItem(item)
        item.saved = !item.saved

        viewState.notifyPostUpdated(item)
    }
}

