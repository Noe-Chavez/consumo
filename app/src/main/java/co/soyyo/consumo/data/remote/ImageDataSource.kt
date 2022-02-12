package co.soyyo.consumo.data.remote

import co.soyyo.consumo.application.AppConstants
import co.soyyo.consumo.repository.WebService

class ImageDataSource(private val webService: WebService) {

    suspend fun getAstronomyPictureLastEightDays() =
        webService.getAstronomyPictureLastEightDays(AppConstants.API_KEY, "2022-02-05")

}