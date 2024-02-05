package fastcampus.part3.random_image.mvi.repository

import fastcampus.part3.random_image.mvi.model.Image

interface ImageRepository {
    suspend fun getRandomImage(): Image
}