package br.com.asoncsts.multi.gymtrack.ui.execution

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.toStringReplacingDotZero
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.ui._components.NewElementButton
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.execution_label_new
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun EditExecution(
    onConfirm: () -> Unit,
    onRemove: (
        executionId: String
    ) -> Unit,
    onToggleDialog: () -> Unit,
    stateFields: EditStateFields,
    modifier: Modifier = Modifier
) {
    EditExecutionDialog(
        onConfirm = onConfirm,
        onDismissRequest = {
            onToggleDialog()
        },
        onRemove = onRemove,
        stateFields
    )

    Box(
        modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        NewElementButton(
            stringResource(
                Res.string.execution_label_new
            ),
            onClick = {
                onToggleDialog()
            }
        )
    }
}

@Composable
internal fun Execution(
    execution: Execution,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = colors().onBackground
                            .copy(.7f)
                    )
                ) {
                    append("${execution.order + 1}º: ")
                }

                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(execution.reps.toString())
                }

                withStyle(
                    SpanStyle(
                        color = colors().onBackground
                            .copy(.7f)
                    )
                ) {
                    append(" x ")
                }

                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(
                        execution
                            .weight
                            .toStringReplacingDotZero()
                    )
                }
            },
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground,
            style = typography().headlineLarge
        )

        execution.notes?.let { notes ->
            Text(
                notes,
                Modifier
                    .fillMaxWidth(),
                color = colors().onBackground
                    .copy(.7f),
                style = typography().bodyMedium
            )
        }

        HorizontalDivider()
    }
}

internal fun executionSequence() = sequenceOf(
    Execution(
        null,
        null,
        null,
        0,
        3,
        0,
        90.0
    ),
    Execution(
        null,
        null,
        "notes",
        0,
        12,
        0,
        72.5
    )
)

