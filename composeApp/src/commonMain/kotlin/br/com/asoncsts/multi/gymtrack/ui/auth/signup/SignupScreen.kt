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
import br.com.asoncsts.multi.gymtrack.ui._navigation.SignupDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui.auth.Fields
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState.*
import br.com.asoncsts.multi.gymtrack.ui.component.Loading
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
        modifier = modifier,
        props = SignupProps(
            logo = painterResource(Res.drawable.logo),
            onFinish = args.authViewModel::signup,
            onSuccess = args.navigateUp,
            onUpdatePassword = args.authViewModel::updatePassword,
            onUpdateUsername = args.authViewModel::updateUsername,
            password = stringResource(Res.string.login_screen_password),
            passwordPlaceholder = stringResource(Res.string.login_screen_password_placeholder),
            signup = stringResource(Res.string.login_screen_signup),
            userName = stringResource(Res.string.login_screen_username),
            userNamePlaceholder = stringResource(Res.string.login_screen_username_placeholder)
        ),
        state = state
    )

    LaunchedEffect(Unit) {
        //TAG_APP.log("SignupScreen.appViewModel.stateTopBarUpdate")
        args.appViewModel.stateTopBarUpdate(
            handlerBack = args.navigateUp
        )
    }
}

@Composable
internal fun SignupScreen(
    modifier: Modifier,
    props: SignupProps,
    state: LoginState
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Image(
            props.logo,
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
                    Modifier,
                    props,
                    state
                )

                Button(
                    props.onFinish,
                    Modifier
                        .width(200.dp)
                ) {
                    Text(
                        props.signup
                    )
                }
            }

            is Loading -> Loading()

            is Success -> props.onSuccess()
        }
    }
}
