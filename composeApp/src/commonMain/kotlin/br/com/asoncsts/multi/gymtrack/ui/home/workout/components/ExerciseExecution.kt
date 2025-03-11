package br.com.asoncsts.multi.gymtrack.ui.home.workout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.ui._theme.*

internal data class ExerciseExecutionProps(
    val exercise: ExerciseExecution,
    val navigateToExerciseExecution: () -> Unit
)

@Composable
internal fun exerciseExecutionProps(
    exercise: ExerciseExecution,
    navigateToExerciseExecution: () -> Unit
) = ExerciseExecutionProps(
    exercise,
    navigateToExerciseExecution
)

@Composable
internal fun ExerciseExecution(
    props: ExerciseExecutionProps,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        props.navigateToExerciseExecution,
        modifier,
        shape = shapes().extraSmall
    ) {
        Column(
            Modifier
                .padding(6.dp)
        ) {
            Text(
                props.exercise.name.capitalizedWords(),
                color = colors().onBackground,
                fontWeight = FontWeight.Bold,
                style = typography().titleLarge
            )

            Spacer(
                Modifier
                    .height(6.dp)
            )

            props.exercise.exercise
                ?.title
                ?.let {
                    Text(
                        it,
                        color = colors().onBackground,
                        style = typography().bodyLarge
                    )
                }
        }
    }
}
