package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import kotlinx.coroutines.flow.map
import org.koin.compose.viewmodel.koinViewModel

sealed class AppNavDestination<Args>(
    val hasBottomBar: Boolean,
    val route: String
) {
    abstract operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun AppNavHost(
    appViewModel: AppViewModel,
    isLoggedIn: Boolean,
    navController: NavHostController,
    modifier: Modifier,
    authViewModel: AuthViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn)
            HomeDestination.route
        else
            SearchDestination.route,
        modifier = modifier
    ) {
        ExerciseDetailDestination(
            ExerciseDetailDestination.Args(
                navigateUp = navController::navigateUp
            ),
            this
        )
        HomeDestination(
            HomeDestination.Args(
                navigateToWorkout = {
                    homeViewModel.navigationArgumentWorkout = it
                    navController.navigateToWorkout()
                },
                homeViewModel
            ),
            this
        )
        homeNav(
            HomeNavArgs(
                exercisesSource = appViewModel,
                navigateUp = navController::navigateUp,
                viewModel = homeViewModel
            )
        )
        LoginDestination(
            LoginDestination.Args(
                authViewModel,
                navigateToSignup = {
                    navController.navigate(SignupDestination.route)
                },
                navigateUp = navController::navigateUp
            ),
            this
        )
        SearchDestination(
            SearchDestination.Args(
                navigateToExerciseDetail = navController::navigateToExerciseDetail,
                appViewModel
            ),
            this
        )
        SignupDestination(
            SignupDestination.Args(
                authViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
    }
}

@Composable
fun NavHostController.appNavHasBottomBarState(): State<Boolean> {
    return currentBackStackEntryFlow
        .map {
            when (it.destination.route) {
                HomeDestination.route,
                SearchDestination.route -> true

                else -> false
            }
        }.collectAsState(false)
}

fun NavHostController.navigateToExerciseDetail(
    alias: String
) {
    navigate(ExerciseDetailDestination.route(alias))
}

fun NavHostController.navigateToHome() {
    navigate(HomeDestination.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToLogin() {
    navigate(LoginDestination.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToSearch() {
    navigate(SearchDestination.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToUser() {
    //navigate(UserDestination.route)
}
