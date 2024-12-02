package br.com.asoncsts.multi.gymtrack.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
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
            state.username,
            props.onUpdateUsername,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors().onBackground,
                unfocusedContainerColor = colors().onBackground
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            label = {
                Text(
                    props.userName
                )
            },
            placeholder = {
                Text(
                    props.userNamePlaceholder
                )
            },
            singleLine = true
        )

        TextField(
            state.password,
            props.onUpdatePassword,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors().onBackground,
                unfocusedContainerColor = colors().onBackground
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    props.onFinish()
                }
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            label = {
                Text(
                    props.password
                )
            },
            placeholder = {
                Text(
                    props.passwordPlaceholder
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
    }
}
