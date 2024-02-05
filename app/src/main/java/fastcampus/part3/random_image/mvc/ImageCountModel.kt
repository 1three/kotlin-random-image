package fastcampus.part3.random_image.mvc

// Model
// 버튼 클릭 시, 이미지 불러오기 및 불러온 횟수 count하는 데이터를 담는 model
class ImageCountModel {
    var count = 0
    fun increase() {
        count += 1
    }
}
