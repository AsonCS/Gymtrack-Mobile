package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.shapes

@Composable
fun textFieldShape() = shapes().medium

@Composable
fun TextField(
    keyboardType: KeyboardType,
    label: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value,
        onValueChange,
        modifier,
        colors = TextFieldDefaults.colors(
            // Container
            disabledContainerColor = colors().background,
            errorContainerColor = colors().background,
            focusedContainerColor = colors().background,
            unfocusedContainerColor = colors().background,

            // Cursor
            cursorColor = colors().onBackground,
            errorCursorColor = colors().onError,

            // Indicator
            disabledIndicatorColor = colors().onBackground,
            errorIndicatorColor = colors().onError,
            focusedIndicatorColor = colors().onBackground,
            unfocusedIndicatorColor = colors().onBackground,
        ),
        keyboardActions = KeyboardActions(
            onDone = onDone
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            keyboardType = keyboardType,
            imeAction = when {
                onDone != null -> ImeAction.Done
                else -> ImeAction.Next
            }
        ),
        label = {
            Text(
                label,
                color = colors().onBackground
            )
        },
        placeholder = {
            Text(
                placeholder,
                color = colors().onBackground
                    .copy(
                        alpha = 0.5f
                    )
            )
        },
        shape = textFieldShape(),
        singleLine = singleLine,
        visualTransformation = if (keyboardType == KeyboardType.Password)
            PasswordVisualTransformation()
        else
            VisualTransformation.None
    )
}
