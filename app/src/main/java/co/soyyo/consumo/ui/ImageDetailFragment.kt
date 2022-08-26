package co.soyyo.consumo.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import co.soyyo.consumo.R
import co.soyyo.consumo.core.GlideSettings
import co.soyyo.consumo.core.Result
import co.soyyo.consumo.data.model.ImageEntity
import co.soyyo.consumo.databinding.FragmentImageDetailBinding
import co.soyyo.consumo.presentation.*
import co.soyyo.consumo.repository.ImageDownloadToStorageService
import co.soyyo.consumo.ui.adapter.ImageAdapter

class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {

    private val communicationBetweenFragments: CommunicationBetweenFragmentsHomeAndDetails by activityViewModels()

    private val downloadImageViewModel by viewModels<DownloadImageViewModel> {
        DownloadImageViewModelFactory(ImageDownloadToStorageService())
    }

    private lateinit var fragmentImageDetailBinding: FragmentImageDetailBinding

    companion object {
        const val REQUEST_CODE = 200
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentImageDetailBinding = FragmentImageDetailBinding.bind(view)

        var url = ""

        communicationBetweenFragments.getImage().observe(viewLifecycleOwner, Observer { image ->
            url = image.url
            setInformationToComponents(image)
            Log.d("communication", "element $image")
        })

        fragmentImageDetailBinding.buttonDownloadImage.setOnClickListener {
            setPermissionStorage(url)
            Log.d("url", "$url")
        }

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

    private fun setPermissionStorage(url: String) {
        val permissionStorageWrite = context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        val permissionStorageRead = context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }

        if (permissionStorageRead == PackageManager.PERMISSION_GRANTED || permissionStorageWrite == PackageManager.PERMISSION_GRANTED) {

            downloadImageViewModel.downloadImage(url).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        Log.d("LiveData", "Loading..")
                    }
                    is Result.Success -> {
                        Toast.makeText(context, "Imagen guardada en el dispositivo", Toast.LENGTH_SHORT).show()
                        Log.d("LiveData", " Size: ${result.data} - Data: ${result.data}")
                    }
                    is Result.Failure -> {
                        Log.d("LiveData", "Error: ${result.exception}")
                    }
                }
            }

            Log.d("permission", "Permiso concedido")
        } else {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE
                )
            }
        }
    }
}