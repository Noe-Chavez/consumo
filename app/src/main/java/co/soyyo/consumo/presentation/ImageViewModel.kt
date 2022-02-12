package co.soyyo.consumo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import co.soyyo.consumo.core.Result
import co.soyyo.consumo.repository.ImageRepository

class ImageViewModel(private val repo: ImageRepository) : ViewModel() {

    fun getAstronomyPictureLastEightDays() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.getAstronomyPictureLastEightDays()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

}

class ImageViewModelFactory(private val repo: ImageRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ImageRepository::class.java).newInstance(repo)
    }
}


