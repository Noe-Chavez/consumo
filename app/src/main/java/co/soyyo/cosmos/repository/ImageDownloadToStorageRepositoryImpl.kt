package co.soyyo.cosmos.repository

import co.soyyo.cosmos.data.remote.ImageDownloadToStorageDataSource

class ImageDownloadToStorageRepositoryImpl(val imageDownloadToStorageDataSource: ImageDownloadToStorageDataSource) : ImageDownloadToStorageRepository {

    override suspend fun downloadImage(url: String) = imageDownloadToStorageDataSource.downloadImage(url)

}