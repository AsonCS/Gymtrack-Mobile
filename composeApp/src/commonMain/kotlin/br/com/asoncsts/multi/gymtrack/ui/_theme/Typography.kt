package br.com.asoncsts.multi.gymtrack.ui._theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun typography(): Typography = MaterialTheme.typography

@Composable
fun Typography(
    colorScheme: ColorScheme
): Typography {
    return Typography(
        titleSmall = TextStyle(
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        ),
    )
}
