package br.com.asoncsts.multi.gymtrack.ui._navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.SignupDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.signup.SignupScreen

data object SignupDestination : AppNavDestination<Args>(
    false,
    "signup"
) {
    class Args(
        val authViewModel: AuthViewModel,
        val navigateUp: () -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            SignupScreen(args)
        }
    }
}
