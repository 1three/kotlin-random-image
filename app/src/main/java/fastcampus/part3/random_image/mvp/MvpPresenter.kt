package fastcampus.part3.random_image.mvp

import fastcampus.part3.random_image.mvp.model.ImageCountModel
import fastcampus.part3.random_image.mvp.repository.ImageRepository

class MvpPresenter(
    private val model: ImageCountModel,
    private val imageRepository: ImageRepository
) : MvpContractor.Presenter, ImageRepository.Callback {
    private var view: MvpContractor.View? = null // Presenter에서 View 직접 참조 (View:Presenter = 1:1)

    override fun attachView(view: MvpContractor.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun loadRandomImage() {
        imageRepository.getRandomImage(this)
    }

    override fun loadImage(url: String, color: String) {
        // View에 전달 및 Model에 count 증가
        model.increase()
        view?.showImage(url, color)
        view?.showImageCountText(model.count)
    }
}