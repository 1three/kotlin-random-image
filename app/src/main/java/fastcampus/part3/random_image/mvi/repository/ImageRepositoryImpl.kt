package fastcampus.part3.random_image.mvi.repository

import fastcampus.part3.random_image.RetrofitManager
import fastcampus.part3.random_image.mvi.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// 초기화 시, dispatcher 불러오기
// → 왜 ? getRandomImage 호출 시 dispatcher 가져오기 위함
//   → 왜 ? 테스트 코드 작성 용이를 위함
class ImageRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ImageRepository {
    override suspend fun getRandomImage() = withContext(dispatcher) {
        RetrofitManager.imageService.getRandomImageSuspend().let {
            Image(it.urls.regular, it.color)
        }
    }
}