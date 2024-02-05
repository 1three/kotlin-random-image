package fastcampus.part3.random_image.mvvm.repository

import fastcampus.part3.random_image.RetrofitManager
import fastcampus.part3.random_image.mvvm.model.Image
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImageRepositoryImpl : ImageRepository {
    override fun getRandomImage() = RetrofitManager.imageService.getRandomImageRx()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap { it ->
            Single.just(Image(it.urls.regular, it.color))
        }
}