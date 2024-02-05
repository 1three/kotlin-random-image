package fastcampus.part3.random_image.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fastcampus.part3.random_image.mvi.repository.ImageRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MviViewModel(private val imageRepository: ImageRepository) : ViewModel() {
    val channel = Channel<MviIntent>()
    private val _state = MutableStateFlow<MviState>(MviState.Idle)
    val state: StateFlow<MviState> get() = _state
    private var count = 0

    init {
        handleIntent() // (3) ViewModel에서는 handleIntent()를 항상 관찰 중
    }

    // Intent 핸들링
    private fun handleIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collectLatest {
                when (it) {
                    MviIntent.loadImage -> { // (4) ∴ Intent를 통해 loadImage가 올 경우
                        loadImage()
                    }
                }
            }
        }
    }

    // (5) loadImage() 실행
    private fun loadImage() {
        viewModelScope.launch {
            _state.value = MviState.Loading // (6) state 갱신 (로딩 상태임을 전달)
            val image = imageRepository.getRandomImage() // (7) 이미지 부르고
            count++ // (8) 카운트 올리고
            _state.value = MviState.LoadedImage(image, count) // (9) state 갱신 (이미지 불러온 상태임을 전달)
        }
    }

    class MviViewModelFactory(private val imageRepository: ImageRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MviViewModel(imageRepository) as T
        }
    }
}