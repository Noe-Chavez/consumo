package co.soyyo.cosmos.repository

import co.soyyo.cosmos.data.model.ImageEntity

interface ImageRepository {
    suspend fun getAstronomyPictureLastNDays(pastDays: Long): List<ImageEntity>
}