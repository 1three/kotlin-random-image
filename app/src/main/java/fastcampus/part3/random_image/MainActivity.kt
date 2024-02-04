package fastcampus.part3.random_image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * 랜덤 이미지 추출 앱
 *
 * 1) Open API (Random Image) 활용
 * 2) 아키텍처 별 구현
 *    아키텍처 : MVC, MVP, MVVM, MVI
 * 3) 기술
 *    네트워크 : Retrofit
 *    이미지 로드 : Coil
 * */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}