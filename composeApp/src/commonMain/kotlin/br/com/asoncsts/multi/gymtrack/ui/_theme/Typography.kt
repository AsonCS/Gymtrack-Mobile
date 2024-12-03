package br.com.asoncsts.multi.gymtrack.ui._theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
fun typography(): Typography = MaterialTheme.typography

/**
 * @see androidx.compose.material3.Typography
 * @see androidx.compose.material3.tokens.TypeScaleTokens
 */
@Composable
fun TypographyTheme(): Typography {
    return Typography()
}
