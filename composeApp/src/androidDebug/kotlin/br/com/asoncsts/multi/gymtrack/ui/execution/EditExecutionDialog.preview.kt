package br.com.asoncsts.multi.gymtrack.ui.execution

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.ui.PreviewComponent
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer

@PreviewComponent
@Composable
private fun EditExecutionDialogPreview(
    @PreviewParameter(EditExecutionDialogValuesProvider::class) state: EditStateFields
) {
    PreviewContainer {
        EditExecutionDialog(
            onConfirm = {},
            onDismissRequest = {},
            onRemove = {},
            stateFields = state
        )
    }
}

private class EditExecutionDialogValuesProvider : PreviewParameterProvider<EditStateFields> {
    override val values = editExecutionDialogSequence()
}
