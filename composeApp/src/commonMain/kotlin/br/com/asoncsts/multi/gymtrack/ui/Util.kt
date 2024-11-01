package br.com.asoncsts.multi.gymtrack.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui._theme.AppTheme
import br.com.asoncsts.multi.gymtrack.ui._theme.colors

@Composable
fun PreviewContainer(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    AppTheme(darkTheme) {
        Surface(
            Modifier
                .fillMaxSize(),
            color = colors().background
        ) {
            content()
        }
    }
}
