package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModel
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
    isLoggedIn: Boolean,
    navController: NavHostController,
    modifier: Modifier,
    authViewModel: AuthViewModel = koinViewModel(),
    homeViewModel: HomeViewModel = koinViewModel(),
    searchViewModel: SearchViewModel = koinViewModel()
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
                navigateToNewWorkout = navController::navigateToNewWorkout,
                navigateToWorkout = {
                    homeViewModel.navigationArgumentWorkout = it
                    navController.navigateToWorkout()
                },
                homeViewModel
            ),
            this
        )
        HomeNavDestination(
            HomeNavDestination.Args(
                homeViewModel
            ),
            this
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
        NewWorkoutDestination(
            NewWorkoutDestination.Args(),
            this
        )
        SearchDestination(
            SearchDestination.Args(
                navigateToExerciseDetail = navController::navigateToExerciseDetail,
                searchViewModel
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
fun NavHostController.appNavDestinationState(): State<AppNavDestination<*>> {
    return currentBackStackEntryFlow
        .map {
            when (it.destination.route) {
                ExerciseDetailDestination.route -> ExerciseDetailDestination
                HomeDestination.route -> HomeDestination
                HomeNavDestination.route -> HomeNavDestination
                LoginDestination.route -> LoginDestination
                NewWorkoutDestination.route -> NewWorkoutDestination
                SearchDestination.route -> SearchDestination
                SignupDestination.route -> SignupDestination
                else -> throw IllegalStateException(
                    "NavHostController.appNavDestinationState: Unknown route"
                )
            }
        }.collectAsState(SearchDestination)
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

fun NavHostController.navigateToNewWorkout() {
    navigate(NewWorkoutDestination.route)
}

fun NavHostController.navigateToSearch() {
    navigate(SearchDestination.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToUser() {
    //navigate(UserDestination.route)
}

fun NavHostController.navigateToWorkout() {
    navigate(
        HomeNavDestination.routeToWorkout()
    )
}
