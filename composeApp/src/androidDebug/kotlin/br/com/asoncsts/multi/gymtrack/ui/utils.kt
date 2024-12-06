package br.com.asoncsts.multi.gymtrack.ui

import br.com.asoncsts.multi.gymtrack.data.image.repository.ImageRepository

fun imageRepository(): ImageRepository {
    return object : ImageRepository {
        override fun image(
            alias: String,
            image: String?
        ): String? = null
    }
}
