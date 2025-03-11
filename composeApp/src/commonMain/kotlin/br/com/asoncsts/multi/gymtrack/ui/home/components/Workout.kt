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
import br.com.asoncsts.multi.gymtrack.ui._theme.*
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.home_label_amount
import org.jetbrains.compose.resources.stringResource

internal data class WorkoutProps(
    val labelAmount: String,
    val navigateToWorkout: () -> Unit,
    val workout: Workout
)

@Composable
internal fun workoutProps(
    workout: Workout,
    navigateToWorkout: () -> Unit,
    labelAmount: String = stringResource(
        Res.string.home_label_amount
    )
) = WorkoutProps(
    labelAmount,
    navigateToWorkout,
    workout
)

@Composable
internal fun Workout(
    props: WorkoutProps,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        props.navigateToWorkout,
        modifier
            .fillMaxWidth(),
        shape = shapes().extraSmall
    ) {
        Column(
            Modifier
                .padding(6.dp)
        ) {
            Text(
                props.workout.name.capitalizedWords(),
                color = colors().onBackground,
                fontWeight = FontWeight.Bold,
                style = typography().titleLarge
            )

            val description = props.workout.description
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
                    append(props.labelAmount)
                    append(" ")

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(props.workout.amount)
                    }
                },
                color = colors().onBackground,
                style = typography().bodyLarge
            )
        }
    }
}
