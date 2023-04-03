package co.soyyo.cosmos.repository

import co.soyyo.cosmos.data.remote.ImageDataSource

class ImageRepositoryImpl(private val dataSource: ImageDataSource) : ImageRepository {

    override suspend fun getAstronomyPictureLastNDays(pastDays: Long) = dataSource.getAstronomyPictureLastNDays(pastDays)

}