package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
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
    authViewModel: AuthViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn)
            TODO("Implement Home")
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
                navigateToExerciseDetail = navController::navigateToExerciseDetail
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
                SearchDestination.route -> SearchDestination
                LoginDestination.route -> LoginDestination
                SignupDestination.route -> SignupDestination
                else -> throw IllegalStateException("Unknown route")
            }
        }.collectAsState(SearchDestination)
}

fun NavHostController.navigateToExerciseDetail(
    alias: String
) {
    navigate(ExerciseDetailDestination.route(alias))
}

fun NavHostController.navigateToHome() {
    TODO("Implement Home")
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
