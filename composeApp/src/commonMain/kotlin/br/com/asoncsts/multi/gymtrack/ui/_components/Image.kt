package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.generated.BuildConfig
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.shapes
import br.com.asoncsts.multi.gymtrack.ui.toPx
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import org.koin.compose.koinInject

enum class Ratio(val value: Float) {
    Square(1f),
    Wide(.5625f) // 16:9
}

@Composable
fun ImageWithCache(
    contentDescription: String?,
    imageUrl: String?,
    placeholder: Painter?,
    width: Dp,
    modifier: Modifier = Modifier,
    ratio: Ratio = Ratio.Square,
    loader: ImageLoader = koinInject(),
    request: ImageRequest.Builder = koinInject()
) {
    val height = (width * ratio.value)
    val url = if (imageUrl != null)
        "${BuildConfig.hostImage}/$imageUrl&height=${height.toPx()}&width=${width.toPx()}"
    else
        null
    val imageRequest = request
        .data(url)
        .memoryCacheKey(url)
        .diskCacheKey(url)
        .listener(
            onError = { req, error ->
                "ImageWithCache".error(
                    error.throwable
                        .message
                        ?: "Error",
                    error.throwable
                )
            }
        )
        .build()

    AsyncImage(
        //url,//"https://image.tmdb.org/t/p/w500//eU7IfdWq8KQy0oNd4kKXS0QUR08.jpg",
        imageRequest,
        contentDescription,
        modifier
            .height(height)
            .width(width)
            .clip(shapes().small)
            .background(colors().onBackground),
        placeholder = placeholder,
    )

    LaunchedEffect(Unit) {
        loader.enqueue(imageRequest)
    }
}