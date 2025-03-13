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
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun Fields(
    onFinish: () -> Unit,
    onUpdatePassword: (String) -> Unit,
    onUpdateUsername: (String) -> Unit,
    state: Filling,
    modifier: Modifier
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
            label = stringResource(
                Res.string.login_screen_username
            ),
            onValueChange = onUpdateUsername,
            placeholder = stringResource(
                Res.string.login_screen_username_placeholder
            ),
            value = state.username
        )
        TextField(
            keyboardType = KeyboardType.Password,
            label = stringResource(
                Res.string.login_screen_password
            ),
            onDone = {
                onFinish()
            },
            onValueChange = onUpdatePassword,
            placeholder = stringResource(
                Res.string.login_screen_password_placeholder
            ),
            value = state.password
        )
    }
}
