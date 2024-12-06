package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui.home.components.HomeScreen
import br.com.asoncsts.multi.gymtrack.ui.home.components.homeScreenProps

const val TAG_HOME = "gymtrack:homeScreen"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    HomeScreen(
        homeScreenProps(),
        HomeState.Loading,
        modifier
    )
}
