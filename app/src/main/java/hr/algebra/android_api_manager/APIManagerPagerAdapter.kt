package hr.algebra.android_api_manager

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.android_api_manager.model.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class APIManagerPagerAdapter(
    private val context: Context,
    private val items: MutableList<Item>
) :
    RecyclerView.Adapter<APIManagerPagerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val youtubePath : String = "https://www.youtube.com/watch?v="

        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
        val ivFavourite = itemView.findViewById<ImageView>(R.id.ivFavourite)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvPublishedDate = itemView.findViewById<TextView>(R.id.tvPublishedDate)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        private val btnVideoLink = itemView.findViewById<Button>(R.id.btnVideoLink)
        fun bind(item: Item) {
            Picasso.get()
                .load(File(item.picturePath))
                .error(R.drawable.limun_u_oci)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvTitle.text = item.title
            tvPublishedDate.text = item.publishedDate
            tvDescription.text = item.description
            btnVideoLink.text = (youtubePath + item.videoPath)
            ivFavourite.setImageResource(if (item.favourite) R.drawable.like else R.drawable.dislike)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.apimanager_pager, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.ivFavourite.setOnClickListener {
            item.favourite = !item.favourite
            val uri = ContentUris.withAppendedId(APIMANAGER_PROVIDER_URI, item._id!!)
            val values = ContentValues().apply {
                put(Item::favourite.name, item.favourite)
            }
            context.contentResolver.update(
                uri,
                values,
                null,
                null
            )
            notifyItemChanged(position)
        }
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}
