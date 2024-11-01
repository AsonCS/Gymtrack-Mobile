package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel

abstract class AppDestination<Args>(
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
            HomeNavDestination.Args(
                appViewModel
            ),
            this
        )
        LoginDestination(
            LoginDestination.Args(
                appViewModel,
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
                appViewModel,
                navigateUp = navController::navigateUp
            ),
            this
        )
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
