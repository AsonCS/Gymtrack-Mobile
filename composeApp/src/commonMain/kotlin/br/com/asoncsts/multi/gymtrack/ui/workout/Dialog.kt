package br.com.asoncsts.multi.gymtrack.ui.workout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import br.com.asoncsts.multi.gymtrack.ui._components.EditDialog
import br.com.asoncsts.multi.gymtrack.ui._components.TextField
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun EditWorkoutDialog(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    stateFields: StateFields
) {
    EditDialog(
        buttonNegative = null,
        buttonPositive = Pair(
            stringResource(
                Res.string.label_create
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
