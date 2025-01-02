package br.com.asoncsts.multi.gymtrack.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun getWidthDp(fraction: Float): Dp {
    return (LocalConfiguration.current.screenWidthDp * fraction).dp
}
