package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import br.com.asoncsts.multi.gymtrack.di.platform
import br.com.asoncsts.multi.gymtrack.generated.BuildConfig
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.shapes
import br.com.asoncsts.multi.gymtrack.ui.toPx
import coil3.ImageLoader
import coil3.compose.asPainter
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
        "${BuildConfig.HOST_IMAGE}/$imageUrl&height=${height.toPx()}&width=${width.toPx()}"
    else
        null

    var image by remember {
        mutableStateOf(placeholder)
    }

    image?.let {
        Image(
            it,
            contentDescription,
            modifier
                .height(height)
                .width(width)
                .clip(shapes().small)
                .background(colors().onBackground)
        )
    }

    if (url == null)
        return

    request
        .data(url)
        .memoryCacheKey(url)
        .diskCacheKey(url)
        .target(
            onError = {
                image = it?.asPainter(platform.coilContext)
            },
            onSuccess = {
                image = it.asPainter(platform.coilContext)
            }
        )

    LaunchedEffect(Unit) {
        loader.enqueue(request.build())
    }
}