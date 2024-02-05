package fastcampus.part3.random_image.mvvm.bindingAdapter

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import fastcampus.part3.random_image.mvvm.model.Image

// binding Adapter
// 역할 : 실제 이미지와 이미지 뷰를 매핑

@BindingAdapter("image")
fun ImageView.setImage(image: Image?) {
    if (image == null) {
        return
    }

    setBackgroundColor(Color.parseColor(image.color))

    load(image.url) {
        crossfade(300)
    }
}