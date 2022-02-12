package co.soyyo.consumo.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import co.soyyo.consumo.R
import co.soyyo.consumo.core.GlideSettings
import co.soyyo.consumo.data.model.ImageEntity
import co.soyyo.consumo.databinding.FragmentImageDetailBinding
import co.soyyo.consumo.presentation.CommunicationBetweenFragmentsHomeAndDetails
import com.bumptech.glide.Glide

class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {

    private val communicationBetweenFragments: CommunicationBetweenFragmentsHomeAndDetails by activityViewModels()

    private lateinit var fragmentImageDetailBinding: FragmentImageDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentImageDetailBinding = FragmentImageDetailBinding.bind(view)

        communicationBetweenFragments.getImage().observe(viewLifecycleOwner, Observer { image ->
            setInformationToComponents(image)
            Log.d("communication", "element $image")
        })

    }

    private fun setInformationToComponents(imageEntity: ImageEntity) {
        GlideSettings.setImageWithGlide(
            requireContext(),
            imageEntity.url,
            fragmentImageDetailBinding.imageBackground
        )
        fragmentImageDetailBinding.imageTitle.text = imageEntity.title
        fragmentImageDetailBinding.textViewDate.text = imageEntity.date
        fragmentImageDetailBinding.textViewExplanation.movementMethod = ScrollingMovementMethod()
        fragmentImageDetailBinding.textViewExplanation.text = imageEntity.explanation
        if (imageEntity.copyright.isNullOrEmpty())
            fragmentImageDetailBinding.textViewCopyright.text =
                requireContext().getString(R.string.copyright_free)
        else
            fragmentImageDetailBinding.textViewCopyright.text = imageEntity.copyright
    }

}