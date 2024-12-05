package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

data object HomeNavDestination : AppDestination<Unit>(
    true,
    "home"
) {
    override operator fun invoke(
        args: Unit,
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
    args: Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = modifier
    ) {
        HomeScreenDestination(
            HomeScreenDestination.Args(
                homeViewModel
            ),
            this
        )
    }
}
