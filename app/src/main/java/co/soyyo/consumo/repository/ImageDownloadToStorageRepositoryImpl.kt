package co.soyyo.consumo.repository

import android.os.Environment
import android.util.Log
import co.soyyo.consumo.data.remote.ImageDownloadToStorageDataSource
import okhttp3.*
import okhttp3.internal.notify
import okhttp3.internal.wait
import java.io.File
import java.io.IOException

class ImageDownloadToStorageRepositoryImpl(val imageDownloadToStorageDataSource: ImageDownloadToStorageDataSource) : ImageDownloadToStorageRepository {

    override suspend fun downloadImage(url: String) = imageDownloadToStorageDataSource.downloadImage(url)

}