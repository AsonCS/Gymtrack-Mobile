package br.com.asoncsts.multi.gymtrack.ui.home.workout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.capitalized
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.ui._components.ButtonClear
import br.com.asoncsts.multi.gymtrack.ui._theme.*

@Composable
internal fun ExerciseExecution(
    exerciseExecution: ExerciseExecution,
    navigateToExerciseExecution: () -> Unit,
    onClearExerciseExecution: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        navigateToExerciseExecution,
        modifier,
        shape = shapes().extraSmall
    ) {
        Column(
            Modifier
                .padding(6.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    exerciseExecution.name.capitalizedWords(),
                    color = colors().onBackground,
                    fontWeight = FontWeight.Bold,
                    style = typography().titleLarge
                )

                Spacer(
                    Modifier
                        .weight(1f)
                )

                ButtonClear(
                    null,
                    onClick = onClearExerciseExecution
                )
            }

            val description = (exerciseExecution as? ExerciseExecution.Detail)
                ?.description
            if (!description.isNullOrEmpty()) {
                Text(
                    description.capitalized(),
                    color = colors().onBackground
                        .copy(.7f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = typography().bodySmall
                )
            }

            Spacer(
                Modifier
                    .height(6.dp)
            )

            exerciseExecution.exercise
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
