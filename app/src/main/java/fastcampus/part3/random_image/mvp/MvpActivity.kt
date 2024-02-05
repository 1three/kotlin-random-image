package fastcampus.part3.random_image.mvp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import fastcampus.part3.random_image.R
import fastcampus.part3.random_image.databinding.ActivityMvpBinding
import fastcampus.part3.random_image.mvp.model.ImageCountModel
import fastcampus.part3.random_image.mvp.repository.ImageRepositoryImpl

class MvpActivity : AppCompatActivity(), MvpContractor.View {
    private lateinit var binding: ActivityMvpBinding
    private lateinit var presenter: MvpContractor.Presenter // View에서 Presenter 직접 참조 (View:Presenter = 1:1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }

        // Presenter 초기화
        presenter = MvpPresenter(ImageCountModel(), ImageRepositoryImpl())
        presenter.attachView(this)
    }

    override fun onDestroy() {
        // Presenter 해제
        presenter.detachView()
        super.onDestroy()
    }

    fun loadImage() {
        presenter.loadRandomImage()
    }

    override fun showImage(url: String, color: String) {
        binding.imageView.run {
            setBackgroundColor(Color.parseColor(color))
            load(url) {
                crossfade(300) // 부드러운 이미지 불러오기 효과
            }
        }
    }

    override fun showImageCountText(count: Int) {
        binding.imageCountTextView.text = "Load ${count} images…"
    }
}