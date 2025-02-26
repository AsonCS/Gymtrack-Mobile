package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography

@Composable
fun ScreenTopBar(
    navigateUp: (() -> Unit)?,
    title: String,
    modifier: Modifier = Modifier,
    useArrowBack: Boolean = true,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        navigateUp?.let {
            IconButton(
                navigateUp,
                Modifier
            ) {
                Icon(
                    if (useArrowBack)
                        Icons.AutoMirrored.Filled.ArrowBack
                    else
                        Icons.Filled.Close,
                    null,
                    Modifier
                        .size(48.dp)
                )
            }
        }
        Text(
            title,
            Modifier
                .weight(1f),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().headlineLarge,
            textAlign = TextAlign.Start
        )
        content?.invoke()
    }
}
