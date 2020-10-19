package ru.farkhodkhaknazarov.pickabureader.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem

class PostsDiffUtilCallback(
    private val oldList: List<PostItem> = emptyList(),
    private val newList: List<PostItem> = emptyList()
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.get(oldItemPosition)
        val newItem = newList.get(newItemPosition)

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.get(oldItemPosition)
        val newItem = newList.get(newItemPosition)

        return oldItem.body == newItem.body
                && oldItem.title == newItem.title
                && oldItem.images == newItem.images
    }
}