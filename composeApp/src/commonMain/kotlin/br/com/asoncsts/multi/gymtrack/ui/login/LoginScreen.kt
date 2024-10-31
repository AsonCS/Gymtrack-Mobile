package br.com.asoncsts.multi.gymtrack.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._navigation.LoginDestination.Args

@Composable
fun LoginScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    LoginScreen(
        modifier = modifier,
        props = LoginProps(),
        state = LoginState.Loading
    )

    LaunchedEffect(Unit) {
        args.appViewModel.stateTopBarUpdate(
            handlerBack = args.navigateUp
        )
    }
}

@Composable
internal fun LoginScreen(
    modifier: Modifier,
    props: LoginProps,
    state: LoginState
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Login Screen"
        )
    }
}
