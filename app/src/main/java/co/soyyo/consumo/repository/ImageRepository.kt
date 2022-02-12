package co.soyyo.consumo.repository

import co.soyyo.consumo.data.model.ImageEntity

interface ImageRepository {

    suspend fun getAstronomyPictureLastEightDays(): List<ImageEntity>

}