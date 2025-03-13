package br.com.asoncsts.multi.gymtrack.ui.execution

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.ui.PreviewComponent
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution as ModelExecution

@PreviewComponent
@Composable
private fun ExecutionPreview(
    @PreviewParameter(ExecutionValuesProvider::class) execution: ModelExecution
) {
    PreviewContainer {
        Execution(execution)
    }
}

private class ExecutionValuesProvider : PreviewParameterProvider<ModelExecution> {
    override val values = executionSequence()
}
