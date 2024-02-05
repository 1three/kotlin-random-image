package fastcampus.part3.random_image.mvp

interface MvpContractor {
    interface View {
        fun showImage(url: String, color: String) // 이미지 보여주기
        fun showImageCountText(count: Int) // 이미지 개수 텍스트 보여주기
    }

    interface Presenter {
        fun attachView(view: View) // 뷰 초기화
        fun detachView() // 뷰 해제
        fun loadRandomImage() // 실제 이미지 로드
    }
}