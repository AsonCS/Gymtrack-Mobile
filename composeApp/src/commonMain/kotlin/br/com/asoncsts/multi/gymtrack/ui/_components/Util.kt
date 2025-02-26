package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors

@Composable
fun border() = BorderStroke(
    1.dp,
    colors().onBackground
)
