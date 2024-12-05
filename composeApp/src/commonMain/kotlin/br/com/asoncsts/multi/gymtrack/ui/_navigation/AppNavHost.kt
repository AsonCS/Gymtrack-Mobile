package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import kotlinx.coroutines.flow.map
import org.koin.compose.viewmodel.koinViewModel

sealed class AppDestination<Args>(
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
    navController: NavHostController,
    modifier: Modifier,
    authViewModel: AuthViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = HomeNavDestination.route,
        modifier = modifier
    ) {
        HomeNavDestination(
            Unit,
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
fun NavHostController.appDestinationState(): State<AppDestination<*>> {
    return currentBackStackEntryFlow
        .map {
            when (it.destination.route) {
                HomeNavDestination.route -> HomeNavDestination
                LoginDestination.route -> LoginDestination
                SignupDestination.route -> SignupDestination
                else -> throw IllegalStateException("Unknown route")
            }
        }.collectAsState(HomeNavDestination)
}

fun NavHostController.navigateToHome() {
    navigate(HomeNavDestination.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToLogin() {
    navigate(LoginDestination.route) {
        launchSingleTop = true
    }
}

fun NavHostController.navigateToUser() {
    //navigate(UserDestination.route)
}
