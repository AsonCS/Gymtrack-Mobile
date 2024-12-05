package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack.ui._navigation.*

@Composable
fun AppScreen(
    appViewModel: AppViewModel,
    modifier: Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        modifier,
        bottomBar = {
            AppBottomBar(
                appBottomBarProps(
                    appViewModel,
                    destinationState = navController.appDestinationState(),
                    navigateToHome = navController::navigateToHome,
                    navigateToLogin = navController::navigateToLogin,
                    navigateToUser = navController::navigateToUser
                )
            )
        }
    ) {
        AppNavHost(
            navController,
            modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}
