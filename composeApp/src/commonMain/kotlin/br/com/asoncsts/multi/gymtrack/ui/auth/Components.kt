package br.com.asoncsts.multi.gymtrack.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._components.TextField
import br.com.asoncsts.multi.gymtrack.ui.auth.LoginState.Filling

internal interface Props {
    val onFinish: () -> Unit
    val onUpdatePassword: (String) -> Unit
    val onUpdateUsername: (String) -> Unit
    val password: String
    val passwordPlaceholder: String
    val userName: String
    val userNamePlaceholder: String
}

@Composable
internal fun Fields(
    modifier: Modifier,
    props: Props,
    state: Filling
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 8.dp
            )
    ) {
        TextField(
            keyboardType = KeyboardType.Email,
            label = props.userName,
            onValueChange = props.onUpdateUsername,
            placeholder = props.userNamePlaceholder,
            value = state.username
        )
        TextField(
            keyboardType = KeyboardType.Password,
            label = props.password,
            onDone = {
                props.onFinish()
            },
            onValueChange = props.onUpdatePassword,
            placeholder = props.passwordPlaceholder,
            value = state.password
        )
    }
}
