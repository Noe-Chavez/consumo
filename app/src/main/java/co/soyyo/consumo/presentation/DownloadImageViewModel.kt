package co.soyyo.consumo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import co.soyyo.consumo.core.Result
import co.soyyo.consumo.repository.ImageDownloadToStorageService
import kotlinx.coroutines.Dispatchers

class DownloadImageViewModel(private val repo: ImageDownloadToStorageService) : ViewModel() {

    fun downloadImage(url: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.downloadImage(url)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

}

class DownloadImageViewModelFactory(private val repo: ImageDownloadToStorageService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ImageDownloadToStorageService::class.java).newInstance(repo)
    }
}