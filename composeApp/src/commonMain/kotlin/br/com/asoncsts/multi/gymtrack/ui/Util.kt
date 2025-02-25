package br.com.asoncsts.multi.gymtrack.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import br.com.asoncsts.multi.gymtrack.ui._theme.AppTheme

@Composable
expect fun getWidthDp(
    fraction: Float = 1f
): Dp

@Composable
fun PreviewContainer(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    AppTheme {
        Box(
            modifier
                .fillMaxSize(),
            contentAlignment = alignment
        ) {
            content()
        }
    }
}

@Composable
expect fun Toast(
    text: String,
    isLong: Boolean = false
)

@Composable
fun Dp.toPx(): Int {
    return with(LocalDensity.current) {
        toPx().toInt()
    }
}
