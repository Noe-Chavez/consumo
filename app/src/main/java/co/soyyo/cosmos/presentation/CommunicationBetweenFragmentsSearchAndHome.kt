package co.soyyo.cosmos.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunicationBetweenFragmentsSearchAndHome : ViewModel() {

    private var date = MutableLiveData<String>(null)

    fun setDate(date: String) {
        this.date.value = date
    }

    fun getDate() = date

}