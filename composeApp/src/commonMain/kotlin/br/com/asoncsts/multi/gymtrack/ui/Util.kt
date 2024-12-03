package br.com.asoncsts.multi.gymtrack.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
