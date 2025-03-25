package br.com.asoncsts.multi.gymtrack.ui.workout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import br.com.asoncsts.multi.gymtrack.ui._components.*
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DeleteWorkoutDialog(
    isVisible: Boolean,
    onDelete: () -> Unit,
    onDismissRequest: () -> Unit
) {
    if (isVisible.not()) return

    AlertDialog(
        confirmButton = {
            Button(
                stringResource(
                    Res.string.label_remove
                ),
                onClick = onDelete
            )
        },
        dismissButton = {
            Button(
                stringResource(
                    Res.string.label_cancel
                ),
                onClick = onDismissRequest
            )
        },
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                stringResource(
                    Res.string.label_remove
                )
            )
        }
    )
}

@Composable
internal fun EditWorkoutDialog(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    stateFields: StateFields
) {
    EditDialog(
        buttonNegative = null,
        buttonPositive = Pair(
            if (stateFields.id == null)
                stringResource(
                    Res.string.label_create
                )
            else
                stringResource(
                    Res.string.label_update
                ),
            onConfirm
        ),
        onDismissRequest = onDismissRequest,
        isDialogVisible = stateFields.isDialogVisible
    ) {
        val labelName = stringResource(
            Res.string.label_name
        )
        TextField(
            KeyboardType.Text,
            labelName,
            { stateFields.name = it },
            labelName,
            stateFields.name,
            Modifier
                .fillMaxWidth(),
            capitalization = KeyboardCapitalization.Words
        )

        val labelDescription = stringResource(
            Res.string.label_description
        )
        TextField(
            KeyboardType.Text,
            labelDescription,
            { stateFields.description = it },
            labelDescription,
            stateFields.description,
            Modifier
                .fillMaxWidth(),
            capitalization = KeyboardCapitalization.Sentences,
            onDone = {
                onConfirm()
            }
        )
    }
}
