package ru.farkhodkhaknazarov.pickabureader.ui.presenter

import moxy.MvpPresenter
import ru.farkhodkhaknazarov.pickabureader.api.PikabuModel
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem
import ru.farkhodkhaknazarov.pickabureader.common.warning
import ru.farkhodkhaknazarov.pickabureader.di.component.PikabuComponent
import ru.farkhodkhaknazarov.pickabureader.ui.view.BaseInterface
import ru.farkhodkhaknazarov.pickabureader.ui.view.IPresenter
import javax.inject.Inject

class SavedPostsFragmentPresenter: MvpPresenter<BaseInterface>(), IPresenter {
    @Inject
    lateinit var module: PikabuModel

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()

        PikabuComponent.init()
        PikabuComponent.get().inject(this)

        module
            .observeSavedPostsDataList()
            .subscribe(this::updatePostList) { warning(it) }

        module.updateSavedPosts()
    }

    override fun updatePostList(postList: List<PostItem>) {
        viewState.updateList(postList)
    }

    fun onSaveBtnClick(item: PostItem) {
        module.savePostItem(item)
    }

    override fun onClose() {
        module.closeModel()
    }
}