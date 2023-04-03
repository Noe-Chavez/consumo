package co.soyyo.cosmos.repository

interface ImageDownloadToStorageRepository {

    suspend fun downloadImage(url: String)

}