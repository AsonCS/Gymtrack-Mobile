package br.com.asoncsts.multi.gymtrack.ui.execution

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone

@PreviewMedPhone
@Composable
private fun NewExecutionPreview(
    @PreviewParameter(NewExecutionValuesProvider::class) state: EditStateFields,
) {
    PreviewContainer(
        Modifier
            .padding(16.dp)
    ) {
        EditExecution(
            onConfirm = {},
            onRemove = {},
            onToggleDialog = {},
            stateFields = state
        )
    }
}

private class NewExecutionValuesProvider : PreviewParameterProvider<EditStateFields> {
    override val values = newExecutionDialogSequence()
}
