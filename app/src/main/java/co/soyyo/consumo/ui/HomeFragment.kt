package co.soyyo.consumo.ui

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import co.soyyo.consumo.R
import co.soyyo.consumo.core.Result
import co.soyyo.consumo.core.SystemDate
import co.soyyo.consumo.databinding.FragmentHomeBinding
import co.soyyo.consumo.data.model.ImageEntity
import co.soyyo.consumo.data.remote.ImageDataSource
import co.soyyo.consumo.presentation.ImageViewModel
import co.soyyo.consumo.presentation.ImageViewModelFactory
import co.soyyo.consumo.repository.ImageRepositoryImpl
import co.soyyo.consumo.repository.RetrofitClient
import co.soyyo.consumo.ui.adapter.ImageAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<ImageViewModel> {
        ImageViewModelFactory(ImageRepositoryImpl(ImageDataSource(RetrofitClient.webService)))
    }

    private var listImages: List<ImageEntity> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        viewModel.getAstronomyPictureLastEightDays().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("LiveData", "Loading..")
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    listImages = result.data
                    binding.recyclerViewImages.adapter = ImageAdapter(listImages)
                    Log.d("LiveData", " Size: ${result.data.size} - Data: ${result.data}")
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("LiveData", "Error: ${result.exception}")
                }
            }
        })

    }

}