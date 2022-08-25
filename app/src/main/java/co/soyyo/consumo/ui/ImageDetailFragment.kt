package co.soyyo.consumo.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import co.soyyo.consumo.R
import co.soyyo.consumo.core.GlideSettings
import co.soyyo.consumo.data.model.ImageEntity
import co.soyyo.consumo.databinding.FragmentImageDetailBinding
import co.soyyo.consumo.presentation.CommunicationBetweenFragmentsHomeAndDetails
import okhttp3.*
import java.io.File
import java.io.IOException

class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {

    private val communicationBetweenFragments: CommunicationBetweenFragmentsHomeAndDetails by activityViewModels()

    private lateinit var fragmentImageDetailBinding: FragmentImageDetailBinding

    private lateinit var okHttpClient: OkHttpClient

    companion object {
        const val REQUEST_CODE = 200
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentImageDetailBinding = FragmentImageDetailBinding.bind(view)

        okHttpClient = OkHttpClient.Builder().build()

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
            downloadImage(url)
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

    private fun downloadImage(imageUrl: String) {

        val request = Request.Builder()
            .url(imageUrl)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("Error", "Se produjo un error en la descarga...");
            }

            override fun onResponse(call: Call, response: Response) {

                if (!response.isSuccessful) {
                    Log.d("Error", "Se produjo un error en la descarga...");
                    return
                }

                response.body?.let {
                    try {
                        // Convert response body to byte array
                        val imageByteArray = it.byteStream().readBytes()

                        // Split image url so we can get the image name
                        val words = imageUrl.split("/").toTypedArray()

                        // Get the image name
                        val imageName = words.last()

                        // Init pathName (Downloads Directory)
                        val pathName = "${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_DOWNLOADS}"

                        // Create New file for the image
                        val file = File(pathName, imageName)

                        // Set byteArray To Image File
                        file.writeBytes(imageByteArray)
                        Log.d("Successful", "Descarga exitosa")
                    } catch(e: IOException) {
                        Log.d("Error", "Error: $e")
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}