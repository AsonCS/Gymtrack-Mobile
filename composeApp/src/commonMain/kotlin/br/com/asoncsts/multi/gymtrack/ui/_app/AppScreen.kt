package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack._mock.data.auth.AuthRepositoryMock
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedIn
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.Unknown
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._navigation.*

@Composable
fun AppScreen(
    appViewModel: AppViewModel,
    modifier: Modifier
) {
    val navController = rememberNavController()
    val hasBottomBar by navController.appNavHasBottomBarState()
    val userState by appViewModel
        .stateAuth
        .collectAsState(
            AuthRepositoryMock.mockUser // TODO Remove mock // null
        )

    val user = (userState as? LoggedIn)?.user

    Scaffold(
        modifier,
        bottomBar = {
            if (hasBottomBar) {
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
        if (userState !is Unknown) {
            AppNavHost(
                appViewModel = appViewModel,
                isLoggedIn = user != null,
                navController = navController,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            )
        } else {
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Loading()
            }
        }
    }
}
