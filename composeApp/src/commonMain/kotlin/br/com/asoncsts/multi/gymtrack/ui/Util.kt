package br.com.asoncsts.multi.gymtrack.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui._theme.AppTheme

@Composable
fun PreviewContainer(
    alignment: Alignment = Alignment.Center,
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme) {
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = alignment
        ) {
            content()
        }
    }
}
