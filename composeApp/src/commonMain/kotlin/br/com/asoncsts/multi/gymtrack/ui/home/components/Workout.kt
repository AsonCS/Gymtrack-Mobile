package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.capitalized
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._components.ButtonDelete
import br.com.asoncsts.multi.gymtrack.ui._components.ButtonEdit
import br.com.asoncsts.multi.gymtrack.ui._theme.*
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.home_label_amount
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun Workout(
    onDeleteWorkout: () -> Unit,
    onEditWorkout: () -> Unit,
    navigateToWorkout: () -> Unit,
    workout: Workout,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        navigateToWorkout,
        modifier
            .fillMaxWidth(),
        shape = shapes().extraSmall
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    workout.name.capitalizedWords(),
                    color = colors().onBackground,
                    fontWeight = FontWeight.Bold,
                    style = typography().titleLarge
                )

                Spacer(
                    Modifier
                        .weight(1f)
                )

                ButtonEdit(
                    null,
                    onClick = onEditWorkout
                )

                ButtonDelete(
                    null,
                    onClick = onDeleteWorkout
                )
            }

            val description = workout.description
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

            Text(
                buildAnnotatedString {
                    append(
                        stringResource(
                            Res.string.home_label_amount
                        )
                    )
                    append(" ")

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(workout.amount)
                    }
                },
                color = colors().onBackground,
                style = typography().bodyLarge
            )
        }
    }
}
