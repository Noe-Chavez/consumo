package co.soyyo.cosmos.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.soyyo.cosmos.R
import co.soyyo.cosmos.core.Result
import co.soyyo.cosmos.core.SystemDate
import co.soyyo.cosmos.databinding.FragmentHomeBinding
import co.soyyo.cosmos.data.model.ImageEntity
import co.soyyo.cosmos.data.remote.ImageDataSource
import co.soyyo.cosmos.presentation.CommunicationBetweenFragmentsHomeAndDetails
import co.soyyo.cosmos.presentation.CommunicationBetweenFragmentsSearchAndHome
import co.soyyo.cosmos.presentation.ImageViewModel
import co.soyyo.cosmos.presentation.ImageViewModelFactory
import co.soyyo.cosmos.repository.ImageRepositoryImpl
import co.soyyo.cosmos.repository.RetrofitClient
import co.soyyo.cosmos.ui.adapter.ImageAdapter

class HomeFragment : Fragment(R.layout.fragment_home), ImageAdapter.OnClickListener {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    private val communicationBetweenFragments: CommunicationBetweenFragmentsHomeAndDetails by activityViewModels()
    private val communicationBetweenFragmentsSearch: CommunicationBetweenFragmentsSearchAndHome by activityViewModels()

    private val viewModel by viewModels<ImageViewModel> {
        ImageViewModelFactory(ImageRepositoryImpl(ImageDataSource(RetrofitClient.webService)))
    }

    private var listImages: List<ImageEntity> = listOf()

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentHomeBinding = FragmentHomeBinding.bind(view)
        val searchDate: String? = communicationBetweenFragmentsSearch.getDate().value
        var previousDays: Long? = null
        if (!searchDate.isNullOrEmpty()){
            previousDays = SystemDate.getDaysDifference(searchDate)
        }

        viewModel.getAstronomyPictureLastNDays(previousDays ?: 8L ).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    fragmentHomeBinding.lottieAnimationWait.visibility = View.VISIBLE
                    Log.d("LiveData", "Loading..")
                }
                is Result.Success -> {
                    fragmentHomeBinding.lottieAnimationWait.visibility = View.GONE
                    listImages = result.data
                    fragmentHomeBinding.recyclerViewImages.adapter = ImageAdapter(listImages, this)
                    Log.d("LiveData", " Size: ${result.data.size} - Data: ${result.data}")
                }
                is Result.Failure -> {
                    fragmentHomeBinding.lottieAnimationWait.visibility = View.GONE
                    Log.d("LiveData", "Error: ${result.exception}")
                }
            }
        }

    }

    override fun onImageClick(imageEntity: ImageEntity) {
        communicationBetweenFragments.setImage(imageEntity)
        findNavController().navigate(R.id.action_homeFragment_to_imageDetailFragment)
    }

}