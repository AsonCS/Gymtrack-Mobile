package br.com.asoncsts.multi.gymtrack.ui.auth.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._navigation.LoginDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui.auth.Fields
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState.*
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.authViewModel
        .state
        .collectAsState()

    LoginScreen(
        onFinish = args.authViewModel::login,
        onGoogleLogin = args.authViewModel::loginWithGoogle,
        onSignup = args.navigateToSignup,
        onSuccess = args.navigateUp,
        onUpdatePassword = args.authViewModel::updatePassword,
        onUpdateUsername = args.authViewModel::updateUsername,
        state = state,
        modifier = modifier
    )
}

@Composable
internal fun LoginScreen(
    onFinish: () -> Unit,
    onGoogleLogin: () -> Unit,
    onSignup: () -> Unit,
    onSuccess: () -> Unit,
    onUpdatePassword: (String) -> Unit,
    onUpdateUsername: (String) -> Unit,
    state: LoginState,
    modifier: Modifier
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Image(
            painterResource(
                Res.drawable.logo
            ),
            null,
            Modifier
                .size(100.dp)
        )

        when (state) {
            is Filling -> {
                if (state.errorMessage != null) {
                    Text(
                        state.errorMessage,
                        color = colors().error,
                        fontSize = 8.sp
                    )
                }

                Fields(
                    onFinish = onFinish,
                    onUpdatePassword = onUpdatePassword,
                    onUpdateUsername = onUpdateUsername,
                    state,
                    Modifier
                )

                Button(
                    onFinish,
                    Modifier
                        .width(200.dp)
                ) {
                    Text(
                        stringResource(
                            Res.string.login_screen_login
                        )
                    )
                }

                OutlinedButton(
                    onSignup,
                    Modifier
                        .width(200.dp)
                ) {
                    Text(
                        stringResource(
                            Res.string.login_screen_signup
                        )
                    )
                }

                Button(
                    onGoogleLogin,
                    Modifier
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors().secondary
                    )
                ) {
                    Text(
                        stringResource(
                            Res.string.login_screen_google_login
                        )
                    )
                }
            }

            is Loading -> Loading()

            is Success -> onSuccess()
        }
    }
}
