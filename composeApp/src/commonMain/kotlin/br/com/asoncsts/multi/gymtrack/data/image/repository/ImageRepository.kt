package br.com.asoncsts.multi.gymtrack.data.image.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data.image.api.ImageApi
import br.com.asoncsts.multi.gymtrack.extension.log

interface ImageRepository {

    class Impl(
        private val api: ImageApi
    ) : ImageRepository {
        override fun image(
            alias: String,
            image: String?
        ): String? {
            image ?: return null

            val url = api.image(
                alias = alias,
                image = image
            )
            TAG_DATA.log("Image: $url")

            return url
        }
    }

    fun image(
        alias: String,
        image: String?
    ): String?

}
