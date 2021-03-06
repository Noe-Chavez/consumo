package co.soyyo.consumo.repository

import co.soyyo.consumo.data.remote.ImageDataSource

class ImageRepositoryImpl(private val dataSource: ImageDataSource) : ImageRepository {

    override suspend fun getAstronomyPictureLastNDays(pastDays: Long) = dataSource.getAstronomyPictureLastNDays(pastDays)

}