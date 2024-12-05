package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.HomeNavHost

data object HomeDestination : AppNavDestination<Unit>(
    true,
    "home"
) {
    override fun invoke(
        args: Unit,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeNavHost()
        }
    }
}
