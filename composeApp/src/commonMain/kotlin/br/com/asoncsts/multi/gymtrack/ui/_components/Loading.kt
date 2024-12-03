package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp
) {
    CircularProgressIndicator(
        modifier
            .semantics {
                contentDescription = "Loading component"
            }
            .size(size)
    )
}
