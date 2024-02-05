package fastcampus.part3.random_image

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {
    @Headers("Authorization: Client-ID 3OOUfvNPqjIr7AdNAXqDV0X1b-0U8mDF9GMNPx60V9g")
    @GET("/photos/random")
    fun getRandomImage(): Call<ImageResponse>

    @Headers("Authorization: Client-ID 3OOUfvNPqjIr7AdNAXqDV0X1b-0U8mDF9GMNPx60V9g")
    @GET("/photos/random")
    fun getRandomImageRx(): Single<ImageResponse>

    @Headers("Authorization: Client-ID 3OOUfvNPqjIr7AdNAXqDV0X1b-0U8mDF9GMNPx60V9g")
    @GET("/photos/random")
    suspend fun getRandomImageSuspend(): ImageResponse
}