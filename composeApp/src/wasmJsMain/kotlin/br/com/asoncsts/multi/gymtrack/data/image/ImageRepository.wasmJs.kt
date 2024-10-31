package br.com.asoncsts.multi.gymtrack.data.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

actual val ByteArray.asImageBitmap: ImageBitmap?
    get() = Image
        .makeFromEncoded(this)
        .toComposeImageBitmap()
