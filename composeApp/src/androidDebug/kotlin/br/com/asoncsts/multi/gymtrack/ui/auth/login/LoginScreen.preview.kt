package br.com.asoncsts.multi.gymtrack.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState

@PreviewMedPhone
@Composable
private fun LoginScreen(
    @PreviewParameter(ValuesProvider::class) state: LoginState
) {
    PreviewContainer {
        LoginScreen(
            modifier = Modifier,
            props = props(),
            state = state
        )
    }
}

private class ValuesProvider : PreviewParameterProvider<LoginState> {
    override val values = sequenceOf(
        LoginState.Filling(),
        LoginState.Filling(
            username = "username@com.br",
            password = "password"
        )
    )
}

@Composable
private fun props() = LoginProps(
    googleLogin = stringResource(R.string.login_screen_google_login),
    logo = painterResource(R.drawable.logo),
    login = stringResource(R.string.login_screen_login),
    onFinish = {},
    onGoogleLogin = {},
    onSignup = {},
    onSuccess = {},
    onUpdatePassword = {},
    onUpdateUsername = {},
    password = stringResource(R.string.login_screen_password),
    passwordPlaceholder = stringResource(R.string.login_screen_password_placeholder),
    signup = stringResource(R.string.login_screen_signup),
    userName = stringResource(R.string.login_screen_username),
    userNamePlaceholder = stringResource(R.string.login_screen_username_placeholder),
)
