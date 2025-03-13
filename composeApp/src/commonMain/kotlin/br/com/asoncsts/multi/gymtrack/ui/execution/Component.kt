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
import br.com.asoncsts.multi.gymtrack.ui._components.ButtonAdd
import br.com.asoncsts.multi.gymtrack.ui._components.ButtonCheck
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
        ButtonAdd(
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
    onFinish: (
        Execution
    ) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            execution.text(
                Modifier
                    .weight(1f)
            )

            execution.children
                .take(3)
                .joinToString(" | ") {
                    "${it.reps} x ${it.weight.toStringReplacingDotZero()}"
                }.takeIf { it.isNotEmpty() }
                ?.let {
                    Text(
                        execution.children
                            .take(3)
                            .joinToString(" | ") {
                                "${it.reps} x ${it.weight.toStringReplacingDotZero()}"
                            },
                        Modifier
                            .weight(2f),
                        color = colors().onBackground
                            .copy(.7f),
                        style = typography().bodyMedium
                    )
                }

            ButtonCheck(
                null,
                onClick = {
                    onFinish(
                        execution.copy(
                            id = "",
                            idParent = execution.id
                        )
                    )
                }
            )
        }

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

@Composable
private fun Execution.text(
    modifier: Modifier
) {
    Text(
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    color = colors().onBackground
                        .copy(.7f)
                )
            ) {
                append("${order + 1}º: ")
            }

            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(reps.toString())
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
                    weight
                        .toStringReplacingDotZero()
                )
            }
        },
        modifier,
        color = colors().onBackground,
        style = typography().headlineLarge
    )
}

internal fun executionSequence() = sequenceOf(
    Execution(
        order = 1,
        reps = 3,
        weight = 90.0
    ),
    Execution(
        notes = "notes",
        order = 2,
        reps = 12,
        weight = 72.5
    )
)

