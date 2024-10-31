package br.com.asoncsts.multi.gymtrack.ui.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._navigation.SignupDestination.Args

@Composable
fun SignupScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    SignupScreen(
        modifier = modifier,
        props = SignupProps(),
        state = SignupState.Loading
    )

    LaunchedEffect(Unit) {
        args.appViewModel.stateTopBarUpdate(
            handlerBack = args.navigateUp
        )
    }
}

@Composable
internal fun SignupScreen(
    modifier: Modifier,
    props: SignupProps,
    state: SignupState
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
            "Signup Screen"
        )
    }
}
