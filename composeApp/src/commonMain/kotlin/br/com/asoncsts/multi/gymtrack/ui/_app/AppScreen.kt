package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedIn
import br.com.asoncsts.multi.gymtrack.ui._navigation.*
import kotlinx.coroutines.flow.map

@Composable
fun AppScreen(
    appViewModel: AppViewModel,
    modifier: Modifier
) {
    val navController = rememberNavController()
    val destination by navController.appNavDestinationState()
    val user by appViewModel
        .stateAuth
        .map { (it as? LoggedIn)?.user }
        .collectAsState(null)

    Scaffold(
        modifier,
        bottomBar = {
            if (destination.hasBottomBar) {
                AppBottomBar(
                    appBottomBarProps(
                        navigateToHome = navController::navigateToHome,
                        navigateToLogin = navController::navigateToLogin,
                        navigateToSearch = navController::navigateToSearch,
                        navigateToUser = navController::navigateToUser,
                        user = user
                    )
                )
            }
        }
    ) {
        AppNavHost(
            isLoggedIn = user != null,
            navController,
            modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}
