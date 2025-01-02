@file:OptIn(ExperimentalComposeUiApi::class)

package br.com.asoncsts.multi.gymtrack.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun getWidthDp(fraction: Float): Dp {
    return (LocalWindowInfo.current.containerSize.width * fraction).dp
}
