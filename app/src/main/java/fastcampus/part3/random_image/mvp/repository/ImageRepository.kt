package fastcampus.part3.random_image.mvp.repository


// Repository
// 역할 : API 호출 및 이미지 불러오기

interface ImageRepository {
    fun getRandomImage(callback: Callback)

    interface Callback {
        fun loadImage(url: String, color: String)
    }
}