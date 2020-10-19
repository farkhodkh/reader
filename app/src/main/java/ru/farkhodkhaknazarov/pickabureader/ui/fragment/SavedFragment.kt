package ru.farkhodkhaknazarov.pickabureader.ui.fragment

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.ktx.moxyPresenter
import ru.farkhodkhaknazarov.pickabureader.R
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem
import ru.farkhodkhaknazarov.pickabureader.ui.adapter.PostsAdapter
import ru.farkhodkhaknazarov.pickabureader.ui.adapter.PostsDiffUtilCallback
import ru.farkhodkhaknazarov.pickabureader.ui.presenter.SavedPostsFragmentPresenter
import ru.farkhodkhaknazarov.pickabureader.ui.view.BaseFragments
import ru.farkhodkhaknazarov.pickabureader.ui.view.BaseInterface

class SavedFragment : BaseFragments(R.layout.fragment_saved), BaseInterface {
    private val presenter by moxyPresenter { SavedPostsFragmentPresenter() }

    lateinit var postsRecycler: RecyclerView
    lateinit var viewAdapter: PostsAdapter
    lateinit var viewManager: LinearLayoutManager

    override fun initView() {
        viewAdapter = PostsAdapter(ArrayList(0), presenter::onSaveBtnClick)
        viewManager = LinearLayoutManager(context)

        postsRecycler = view!!.findViewById<RecyclerView>(R.id.saved_posts_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
        postsRecycler.adapter = viewAdapter
    }

    override fun updateList(postList: List<PostItem>) {
        val postsDiffUtilCallback = PostsDiffUtilCallback(viewAdapter.postList, postList)
        val postsDiffResult = DiffUtil.calculateDiff(postsDiffUtilCallback, true)

        viewAdapter.postList.clear()
        viewAdapter.postList.addAll(postList)

        postsDiffResult.dispatchUpdatesTo(viewAdapter)
    }

    override fun notifyPostUpdated(item: PostItem) {
        val index = viewAdapter.postList.indexOf(item)
        viewAdapter.postList.add(item)
        viewAdapter.notifyItemChanged(index)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onClose()
    }
}