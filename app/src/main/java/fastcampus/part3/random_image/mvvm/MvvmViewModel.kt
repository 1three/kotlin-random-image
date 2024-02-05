package fastcampus.part3.random_image.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fastcampus.part3.random_image.mvvm.model.Image
import fastcampus.part3.random_image.mvvm.repository.ImageRepository
import io.reactivex.disposables.CompositeDisposable

class MvvmViewModel(private val imageRepository: ImageRepository) : ViewModel() {
    // LiveData 추가
    private val _countLiveData = MutableLiveData<String>()
    val countLiveData: LiveData<String> by lazy { _countLiveData }
    private val _imageLiveData = MutableLiveData<Image>()
    val imageLiveData: LiveData<Image> by lazy { _imageLiveData }

    // Rx 사용을 위한 disposable
    private var disposable: CompositeDisposable? = CompositeDisposable()
    private var count = 0 // 이미지 count 담는 변수

    // View에서 ViewModel을 호출하는 메서드
    fun loadRandomImage() {
        disposable?.add(imageRepository.getRandomImage()
            .doOnSuccess {
                count++
            }
            .subscribe { item ->
                _imageLiveData.value = item
                _countLiveData.value = "Load $count images…"
            })
    }

    // ViewModel 메모리 해제 시
    override fun onCleared() {
        super.onCleared()
        disposable = null
    }

    class MvvmViewModelFactory(private val imageRepository: ImageRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MvvmViewModel(imageRepository) as T
        }
    }
}