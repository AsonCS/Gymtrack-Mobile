package br.com.asoncsts.multi.gymtrack.ui

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.key.Key.Companion.Escape
import androidx.compose.ui.input.key.KeyEventType.Companion.KeyDown
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import br.com.asoncsts.multi.gymtrack.ui._theme.AppTheme

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
fun BackHandlerContainer(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val focusRequester = remember {
        FocusRequester()
    }

    Box(
        modifier
            .focusRequester(focusRequester)
            .focusable()
            .onKeyEvent {
                if (it.type == KeyDown && it.key == Escape) {
                    navigateUp()
                }
                false
            },
        content = content
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun Dp.toPx(): Int {
    return with(LocalDensity.current) {
        toPx().toInt()
    }
}

@Composable
expect fun getWidthDp(fraction: Float = 1f): Dp
