package co.soyyo.consumo.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.soyyo.consumo.data.model.ImageEntity

class CommunicationBetweenFragmentsHomeAndDetails : ViewModel(){

    private val image = MutableLiveData<ImageEntity>()

    fun setImage(imageEntity: ImageEntity) {
        image.value = imageEntity
    }

    fun getImage() = image

}