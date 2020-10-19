package ru.farkhodkhaknazarov.pickabureader.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.farkhodkhaknazarov.pickabureader.R
import ru.farkhodkhaknazarov.pickabureader.api.model.PostItem
import ru.farkhodkhaknazarov.pickabureader.di.component.CommonComponent
import javax.inject.Inject

class PostsAdapter(
    var postList: ArrayList<PostItem>,
    val onPostSave: (PostItem) -> (Unit)
) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    @Inject
    lateinit var picasso: Picasso

    init {
        CommonComponent.get().inject(this)
    }

    inner class PostViewHolder(var postItemView: ConstraintLayout) :
        RecyclerView.ViewHolder(postItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postItem =
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_item_view,
                parent,
                false
            ) as ConstraintLayout

        return PostViewHolder(postItem)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postList.get(position)

        val saveBtn: Button = holder.postItemView.findViewById(R.id.saveBtn)
        saveBtn.text = if (item.saved) "UnSave" else "Save"
        saveBtn.setOnClickListener { onPostSave(item) }
        val titleTextView: TextView = holder.postItemView.findViewById(R.id.titleTextView)
        val bodeTextView: TextView = holder.postItemView.findViewById(R.id.bodeTextView)

        titleTextView.text = item.title
        bodeTextView.text = item.body

        var index = 0
        item.images?.forEach {
            val iView: ImageView? = when (index) {
                1 -> holder.postItemView.findViewById(R.id.iView1)
                2 -> holder.postItemView.findViewById(R.id.iView2)
                3 -> holder.postItemView.findViewById(R.id.iView3)
                else -> null
            }
            iView?.let {
                picasso
                    .load(item.images.get(index - 1))
                    .into(iView)
            }
            index++
        }
    }

    override fun getItemCount(): Int = postList.size
}