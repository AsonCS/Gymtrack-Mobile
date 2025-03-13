package br.com.asoncsts.multi.gymtrack.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState

@PreviewMedPhone
@Composable
private fun LoginScreenPreview(
    @PreviewParameter(ValuesProvider::class) state: LoginState
) {
    PreviewContainer {
        LoginScreen(
            onFinish = {},
            onGoogleLogin = {},
            onSignup = {},
            onSuccess = {},
            onUpdatePassword = {},
            onUpdateUsername = {},
            state,
            Modifier
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
