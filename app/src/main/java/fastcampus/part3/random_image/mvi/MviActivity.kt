package fastcampus.part3.random_image.mvi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import fastcampus.part3.random_image.R
import fastcampus.part3.random_image.databinding.ActivityMviBinding
import fastcampus.part3.random_image.mvi.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// Mvi : View 역할

class MviActivity : AppCompatActivity() {
    private val viewModel: MviViewModel by viewModels {
        MviViewModel.MviViewModelFactory(ImageRepositoryImpl())
    }
    private lateinit var binding: ActivityMviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
        observeViewModel()
    }

    // (1) View, User 버튼 클릭 시
    fun loadImage() {
        lifecycleScope.launch {
            viewModel.channel.send(MviIntent.loadImage) // (2) Intent를 통해 ViewModel에 전달
        }
    }

    // State 불러오기 및 갱신(업데이트)를 위한 관찰 코드
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state -> // (10) 여기에 들어와, 상태에 따라 동작 처리
                when (state) {
                    is MviState.Idle -> {
                        binding.progressView.isVisible = false
                    }

                    is MviState.Loading -> {
                        binding.progressView.isVisible = true
                    }

                    is MviState.LoadedImage -> {
                        binding.progressView.isVisible = false
                        binding.imageView.run {
                            setBackgroundColor(Color.parseColor(state.image.color))
                            load(state.image.url) {
                                crossfade(300)
                            }
                        }
                        binding.imageCountTextView.text = "Load ${state.count} images…"
                    }
                }
            }
        }
    }
}