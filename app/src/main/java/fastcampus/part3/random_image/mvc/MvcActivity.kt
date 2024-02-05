package fastcampus.part3.random_image.mvc

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import fastcampus.part3.random_image.databinding.ActivityMvcBinding

/**
 * MVC
 *
 * 1) Activity 내 View + Controller 모두 포함한다.
 * 2) Model과 Provider 내에서는 Activity 알 수 없다. (Model과 Provider 재사용 가능)
 * */

class MvcActivity : AppCompatActivity(), ImageProvider.Callback {
    private lateinit var binding: ActivityMvcBinding
    private val model = ImageCountModel()
    private val imageProvider = ImageProvider(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
    }

    fun loadImage() {
        imageProvider.getRandomImage()
    }

    override fun loadImage(url: String, color: String) {
        model.increase()
        with(binding) {
            imageView.run {
                setBackgroundColor(Color.parseColor(color))
                load(url)
            }
            imageCountTextView.text = "Load ${model.count} images…"
        }
    }
}