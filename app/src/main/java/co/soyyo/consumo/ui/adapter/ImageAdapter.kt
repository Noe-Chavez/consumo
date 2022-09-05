package co.soyyo.consumo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.soyyo.consumo.R
import co.soyyo.consumo.application.AppConstants
import co.soyyo.consumo.core.GlideSettings
import co.soyyo.consumo.data.model.ImageEntity

class ImageAdapter(
    private val listImage: List<ImageEntity>,
    private val itemClickListener: OnClickListener
): RecyclerView.Adapter<ImageAdapter.HomeScreenViewHolder>() {

    lateinit var context: Context

    interface OnClickListener {
        fun onImageClick(imageEntity: ImageEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenViewHolder {
        context = parent.context
        val itemBinding = LayoutInflater.from(context)
            .inflate(R.layout.image_item, parent, false)
        val holder = HomeScreenViewHolder(itemBinding)

        itemBinding.rootView.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onImageClick(listImage[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {
        val imageEntity = listImage[position]
        GlideSettings.setImageWithGlide(context, imageEntity.url, holder.imageViewImage)
        holder.textViewTitle.text = imageEntity.title
        holder.textViewDate.text = imageEntity.date
        if (imageEntity.copyright.isNullOrEmpty())
            holder.textViewCopyright.text = context.getString(R.string.copyright_free)
        else
            holder.textViewCopyright.text = imageEntity.copyright
    }

    override fun getItemCount() = listImage.size

    inner class HomeScreenViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageViewImage: ImageView = itemView.findViewById(R.id.card_view_image)
        val textViewTitle: TextView = itemView.findViewById(R.id.card_view_title)
        val textViewDate: TextView = itemView.findViewById(R.id.card_view_date)
        val textViewCopyright: TextView = itemView.findViewById(R.id.card_view_copyright)
    }

}