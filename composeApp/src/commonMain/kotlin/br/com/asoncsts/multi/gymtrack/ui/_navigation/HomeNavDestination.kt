package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeNavDestination.Args

data object HomeNavDestination : AppDestination<Args>(
    "home"
) {
    class Args(
        val appViewModel: AppViewModel
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            HomeNavHost(args)
        }
    }
}

abstract class HomeDestination<Args>(
    val route: String
) {
    abstract operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun HomeNavHost(
    args: Args,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = modifier
    ) {
        HomeScreenDestination(
            HomeScreenDestination.Args(
                args.appViewModel
            ),
            this
        )
    }
}
