package fastcampus.part3.random_image.mvvm.repository

import fastcampus.part3.random_image.mvvm.model.Image
import io.reactivex.Single

interface ImageRepository {
    fun getRandomImage(): Single<Image>
}