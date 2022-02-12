package co.soyyo.consumo.presentation

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.soyyo.consumo.data.model.ImageEntity

class CommunicationBetweenFragmentsSearchAndHome : ViewModel() {

    private var date = MutableLiveData<String>(null)

    fun setDate(date: String) {
        this.date.value = date
    }

    fun getDate() = date

}