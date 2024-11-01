package br.com.asoncsts.multi.gymtrack.ui._theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkColorPalette = darkColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    secondary = AccentColor,

    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
)

private val lightColorPalette = darkColorPalette
/*lightColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    secondary = AccentColor,

    background = Color.White,
    surfaceVariant = Color.White,
    surface = lightSurface
)*/

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }
    MaterialTheme(
        colorScheme = if (darkTheme) {
            darkColorPalette
        } else {
            lightColorPalette
        },
        typography = Typography(colorScheme),
        shapes = Shapes,
        content = content
    )
}
