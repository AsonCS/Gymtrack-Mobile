package br.com.asoncsts.multi.gymtrack.data.image.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data.image.api.ImageApi
import br.com.asoncsts.multi.gymtrack.extension.error

interface ImageRepository {

    class Impl(
        private val api: ImageApi
    ) : ImageRepository {
        override fun image(
            image: String?
        ): String? {
            image ?: return null

            return try {
                api.image(
                    name = image,
                    path = image.split(".")[0]
                )
            } catch (t: Throwable) {
                TAG_DATA.error("ImageRepository", t)
                null
            }
        }
    }

    fun image(
        image: String?
    ): String?

}
