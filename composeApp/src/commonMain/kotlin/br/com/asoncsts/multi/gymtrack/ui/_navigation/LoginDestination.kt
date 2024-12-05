package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.LoginDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.login.LoginScreen

data object LoginDestination : AppDestination<Args>(
    false,
    "login"
) {
    class Args(
        val authViewModel: AuthViewModel,
        val navigateToSignup: () -> Unit,
        val navigateUp: () -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            LoginScreen(args)
        }
    }
}
