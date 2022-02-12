package co.soyyo.consumo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.soyyo.consumo.R
import co.soyyo.consumo.entities.ImageEntity
import com.bumptech.glide.Glide

class ImageAdapter(
    private val listImage: List<ImageEntity>
): RecyclerView.Adapter<ImageAdapter.HomeScreenViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenViewHolder {
        context = parent.context
        val itemBinding = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return HomeScreenViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {
        val imageEntity = listImage[position]
        Glide.with(context)
            .load(imageEntity.url)
            .centerCrop()
            .placeholder(R.drawable.load)
            .error(R.drawable.ic_baseline_error_24)
            .into(holder.imageViewImage)
        holder.textViewTitle.text = imageEntity.title
        holder.textViewDate.text = imageEntity.date
        holder.textViewCopyright.text = imageEntity.copyright
    }

    override fun getItemCount() = listImage.size

    inner class HomeScreenViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageViewImage: ImageView = itemView.findViewById(R.id.card_view_image)
        val textViewTitle: TextView = itemView.findViewById(R.id.card_view_title)
        val textViewDate: TextView = itemView.findViewById(R.id.card_view_date)
        val textViewCopyright: TextView = itemView.findViewById(R.id.card_view_copyright)
    }

}