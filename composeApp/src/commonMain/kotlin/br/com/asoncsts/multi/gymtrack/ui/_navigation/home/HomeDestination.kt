package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui.home.HomeScreen

data object HomeDestination : HomeNavDestination<Unit>(
    "home"
) {
    override fun invoke(
        args: Unit,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeScreen()
        }
    }
}