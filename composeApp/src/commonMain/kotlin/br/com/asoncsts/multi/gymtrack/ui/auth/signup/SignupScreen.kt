package br.com.asoncsts.multi.gymtrack.ui.auth.signup

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._navigation.SignupDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui.auth.Fields
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState.*
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignupScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    val state by args.authViewModel
        .state
        .collectAsState()

    SignupScreen(
        onFinish = args.authViewModel::signup,
        onSuccess = args.navigateUp,
        onUpdatePassword = args.authViewModel::updatePassword,
        onUpdateUsername = args.authViewModel::updateUsername,
        state,
        modifier
    )
}

@Composable
internal fun SignupScreen(
    onFinish: () -> Unit,
    onSuccess: () -> Unit,
    onUpdatePassword: (String) -> Unit,
    onUpdateUsername: (String) -> Unit,
    state: LoginState,
    modifier: Modifier
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(32.dp),
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
                            Res.string.login_screen_signup
                        )
                    )
                }
            }

            is Loading -> Loading()

            is Success -> onSuccess()
        }
    }
}
