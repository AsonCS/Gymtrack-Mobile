package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.di.platform
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography

@Composable
fun ScreenTopBar(
    onNavigateUp: (() -> Unit)?,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .offset(
                x = (-10).dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (platform.isDesktop && onNavigateUp != null) {
            IconButton(
                onNavigateUp,
                Modifier
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
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
    }
}
