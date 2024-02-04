package fastcampus.part3.random_image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fastcampus.part3.random_image.databinding.ActivityMainBinding

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
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
    }

    fun openMvc() {}
    fun openMvp() {}
    fun openMvvm() {}
    fun openMvi() {}
}