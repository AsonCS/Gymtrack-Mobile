package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui.home.HomeState

@Composable
internal fun HomeScreen(
    state: HomeState,
    modifier: Modifier = Modifier
) {
    Box {
        Text(
            "Home Screen"
        )
    }
}
