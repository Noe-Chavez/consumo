package co.soyyo.cosmos.data.remote

import android.os.Environment
import android.util.Log
import okhttp3.*
import java.io.File
import java.io.IOException

class ImageDownloadToStorageDataSource {

    suspend fun downloadImage(url: String) {

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Error", "Se produjo un error en la descarga...");
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.d("Error", "Se produjo un error en la descarga...");
                    return
                }

                response.body?.let {
                    try {
                        // Convert response body to byte array
                        val imageByteArray = it.byteStream().readBytes()

                        // Split image url so we can get the image name
                        val words = url.split("/").toTypedArray()

                        // Get the image name
                        val imageName = words.last()

                        // Init pathName (Downloads Directory)
                        val pathName = "${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_DOWNLOADS}"

                        // Create New file for the image
                        val file = File(pathName, imageName)

                        // Set byteArray To Image File
                        file.writeBytes(imageByteArray)
                    } catch(e: IOException) {
                        Log.d("Error", "Error: $e")
                        e.printStackTrace()
                    }
                }
            }

        })

    }

}