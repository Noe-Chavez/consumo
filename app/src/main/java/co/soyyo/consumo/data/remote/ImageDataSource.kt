package co.soyyo.consumo.data.remote

import co.soyyo.consumo.application.AppConstants
import co.soyyo.consumo.core.SystemDate
import co.soyyo.consumo.repository.WebService

class ImageDataSource(private val webService: WebService) {

    suspend fun getAstronomyPictureLastNDays(pastDays: Long) = webService.getAstronomyPictureLastNDays(
        AppConstants.API_KEY,
        SystemDate.getPreviousDate(pastDays)
    )

}