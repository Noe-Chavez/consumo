package co.soyyo.cosmos.data.remote

import co.soyyo.cosmos.application.AppConstants
import co.soyyo.cosmos.core.SystemDate
import co.soyyo.cosmos.repository.WebService

class ImageDataSource(private val webService: WebService) {

    suspend fun getAstronomyPictureLastNDays(pastDays: Long) = webService.getAstronomyPictureLastNDays(
        AppConstants.API_KEY,
        SystemDate.getPreviousDate(pastDays)
    )

}