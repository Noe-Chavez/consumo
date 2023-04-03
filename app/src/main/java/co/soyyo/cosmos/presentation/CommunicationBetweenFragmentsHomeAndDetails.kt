package co.soyyo.cosmos.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.soyyo.cosmos.data.model.ImageEntity

class CommunicationBetweenFragmentsHomeAndDetails : ViewModel(){

    private val image = MutableLiveData<ImageEntity>()

    fun setImage(imageEntity: ImageEntity) {
        image.value = imageEntity
    }

    fun getImage() = image

}