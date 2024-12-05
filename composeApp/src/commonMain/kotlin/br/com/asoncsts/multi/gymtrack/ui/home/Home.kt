package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui.home.components.HomeScreen

const val TAG_HOME = "gymtrack:homeScreen"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    HomeScreen(
        HomeState.Loading,
        modifier
    )
}
