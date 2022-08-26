package co.soyyo.consumo.repository

import co.soyyo.consumo.data.model.ImageEntity

interface ImageRepository {
    suspend fun getAstronomyPictureLastNDays(pastDays: Long): List<ImageEntity>
}