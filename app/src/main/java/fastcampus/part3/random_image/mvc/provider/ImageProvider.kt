package fastcampus.part3.random_image.mvc.provider

import fastcampus.part3.random_image.ImageResponse
import fastcampus.part3.random_image.RetrofitManager
import retrofit2.Call
import retrofit2.Response

// Provider
// 이미지(데이터)를 불러오는 역할
class ImageProvider(private val callback: Callback) {
    fun getRandomImage() {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.loadImage(it.urls.regular, it.color)
                        }
                    }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    //
                }
            })
    }

    // 콜백 인터페이스
    // 이미지 불러오기 및 View에 다시 반환
    interface Callback {
        fun loadImage(url: String, color: String)
    }
}