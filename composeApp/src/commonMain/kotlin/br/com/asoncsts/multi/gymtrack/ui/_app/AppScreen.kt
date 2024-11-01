package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack.ui._navigation.*
import br.com.asoncsts.multi.gymtrack.ui._theme.colors

@Composable
fun AppScreen(
    appViewModel: AppViewModel,
    modifier: Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        modifier,
        backgroundColor = colors().background,
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
                .padding(it)
                .fillMaxSize()
        )
    }
}
