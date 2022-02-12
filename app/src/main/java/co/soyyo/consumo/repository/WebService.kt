package co.soyyo.consumo.repository

import co.soyyo.consumo.application.AppConstants
import co.soyyo.consumo.data.model.ImageEntity
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("apod")
    suspend fun getAstronomyPictureLastNDays(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String
    ): List<ImageEntity>

}

object RetrofitClient {

    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

}