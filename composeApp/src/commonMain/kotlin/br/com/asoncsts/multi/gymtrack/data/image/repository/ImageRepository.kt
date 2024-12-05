package br.com.asoncsts.multi.gymtrack.data.image.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data.image.api.ImageApi
import br.com.asoncsts.multi.gymtrack.extension.error

interface ImageRepository {

    class Impl(
        private val api: ImageApi
    ) : ImageRepository {
        override fun image(
            alias: String,
            image: String?
        ): String? {
            image ?: return null

            return try {
                api.image(
                    name = image,
                    path = alias
                )
            } catch (t: Throwable) {
                TAG_DATA.error("ImageRepository", t)
                null
            }
        }
    }

    fun image(
        alias: String,
        image: String?
    ): String?

}
