package br.com.asoncsts.multi.gymtrack.data.image

import androidx.compose.ui.graphics.ImageBitmap

expect val ByteArray.asImageBitmap: ImageBitmap?

interface ImageRepository {

    class Impl(
        private val remote: ImageRemote
    ) : ImageRepository {
        override suspend fun getImage(
            url: String?
        ): ImageBitmap? {
            return remote.getImage(url)
                ?.asImageBitmap
        }
    }

    suspend fun getImage(
        url: String?
    ): ImageBitmap?

}
