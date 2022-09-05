package co.soyyo.consumo.repository

import co.soyyo.consumo.data.remote.ImageDownloadToStorageDataSource

interface ImageDownloadToStorageRepository {

    suspend fun downloadImage(url: String)

}