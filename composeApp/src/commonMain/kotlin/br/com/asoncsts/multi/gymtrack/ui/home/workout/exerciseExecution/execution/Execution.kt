package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.toStringReplacingDotZero
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography

@Composable
internal fun Execution(
    props: ExecutionProps,
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
                    append("${props.execution.order + 1}º: ")
                }

                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(props.execution.reps.toString())
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
                        props.execution
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

        props.execution.notes?.let { notes ->
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
    ExecutionProps(
        Execution(
            null,
            null,
            null,
            0,
            3,
            0,
            90.0
        )
    ),
    ExecutionProps(
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
)
