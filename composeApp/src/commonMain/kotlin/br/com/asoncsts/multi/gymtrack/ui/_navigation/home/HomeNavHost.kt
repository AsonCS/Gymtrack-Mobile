package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

sealed class HomeNavDestination<Args>(
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
    homeViewModel: HomeViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        HomeDestination(
            HomeDestination.Args(
                homeViewModel,
                navigateToExerciseDetail = args.navigateToExerciseDetail
            ),
            this
        )
    }
}
