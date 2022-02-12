package co.soyyo.consumo.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import co.soyyo.consumo.application.AppConstants
import co.soyyo.consumo.core.SystemDate
import co.soyyo.consumo.data.model.ImageEntity
import co.soyyo.consumo.repository.WebService

class ImageDataSource(private val webService: WebService) {

    suspend fun getAstronomyPictureLastEightDays() = webService.getAstronomyPictureLastEightDays(
        AppConstants.API_KEY,
        SystemDate.getPreviousDate(8L)
    )

}