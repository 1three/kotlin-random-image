package fastcampus.part3.random_image.mvi

import fastcampus.part3.random_image.mvi.model.Image

sealed class MviState {
    object Idle : MviState()
    object Loading : MviState()
    data class LoadedImage(val image: Image, val count: Int) : MviState()
}
