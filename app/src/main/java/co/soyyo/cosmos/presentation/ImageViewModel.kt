package co.soyyo.cosmos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import co.soyyo.cosmos.core.Result
import co.soyyo.cosmos.repository.ImageRepositoryImpl

class ImageViewModel(private val repo: ImageRepositoryImpl) : ViewModel() {

    fun getAstronomyPictureLastNDays(pastDays: Long) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.getAstronomyPictureLastNDays(pastDays)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

}

class ImageViewModelFactory(private val repo: ImageRepositoryImpl) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ImageRepositoryImpl::class.java).newInstance(repo)
    }
}


