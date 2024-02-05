package fastcampus.part3.random_image.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import fastcampus.part3.random_image.databinding.ActivityMvvmBinding
import fastcampus.part3.random_image.mvvm.repository.ImageRepositoryImpl

class MvvmActivity : AppCompatActivity() {
    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModel.MvvmViewModelFactory(ImageRepositoryImpl())
    }
    private lateinit var binding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner = this // LiveData 사용, lifecycleOwner 등록
            it.view = this
            it.viewModel = viewModel
        }
    }

    fun loadImage() {
        viewModel.loadRandomImage()
    }
}