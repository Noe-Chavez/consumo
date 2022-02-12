package co.soyyo.consumo.core

import android.content.Context
import android.widget.ImageView
import co.soyyo.consumo.R
import com.bumptech.glide.Glide

class GlideSettings {
    companion object {
        fun setImageWithGlide(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.load)
                .error(R.drawable.ic_baseline_error_24)
                .into(imageView)
        }
    }
}