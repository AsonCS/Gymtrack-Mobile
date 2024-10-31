package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
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
        topBar = {
            AppTopBar(
                appViewModel,
                navigateToLogin = navController::navigateToLogin,
                navigateToUser = navController::navigateToUser
            )
        }
    ) {
        AppNavHost(
            appViewModel,
            navController,
            modifier
                .fillMaxSize()
        )
    }
}
