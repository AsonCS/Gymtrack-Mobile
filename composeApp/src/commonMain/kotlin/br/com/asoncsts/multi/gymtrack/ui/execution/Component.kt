package br.com.asoncsts.multi.gymtrack.ui.execution

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.getTimeSeconds
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            execution.text(
                Modifier
                    .weight(1f)
            )

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

        execution.children.text(
            Modifier
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

@Composable
private fun List<Execution>.text(
    modifier: Modifier
) {
    Text(
        buildAnnotatedString {
            val list = take(5)
            list.forEachIndexed { index, execution ->
                val updated = execution.updated > getTimeSeconds() - 60 * 24
                withStyle(
                    SpanStyle(
                        fontWeight = if (updated)
                            FontWeight.Bold
                        else
                            FontWeight.Normal,
                        textDecoration = if (updated)
                            TextDecoration.Underline
                        else
                            TextDecoration.None
                    )
                ) {
                    append(
                        "${execution.reps} x ${execution.weight.toStringReplacingDotZero()}"
                    )
                }
                if (index < list.size - 1) {
                    append(" | ")
                }
            }
        },
        modifier,
        color = colors().onBackground
            .copy(.7f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = typography().bodyMedium
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

