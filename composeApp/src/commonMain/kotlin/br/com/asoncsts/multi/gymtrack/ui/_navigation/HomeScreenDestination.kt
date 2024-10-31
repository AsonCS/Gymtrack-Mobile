package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeScreenDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.HomeScreen

data object HomeScreenDestination : HomeDestination<Args>(
    "home_screen"
) {
    class Args(
        val appViewModel: AppViewModel,
        val navigateToUser: () -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeScreen(args)
        }
    }
}
