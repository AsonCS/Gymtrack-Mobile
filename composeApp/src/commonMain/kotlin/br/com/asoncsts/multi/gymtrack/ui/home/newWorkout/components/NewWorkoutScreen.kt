package br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._components.TextField
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.NewWorkoutState
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.NewWorkoutStateFields

internal data class NewWorkoutScreenProps(
    val labelDescription: String,
    val labelName: String,
    val labelNewWorkout: String,
    val navigateToWorkout: (
        workout: Workout
    ) -> Unit,
    val onSave: () -> Unit,
    val placeholderDescription: String,
    val placeholderName: String
)

@Composable
internal fun newWorkoutScreenProps(
    labelDescription: String,
    labelName: String,
    labelNewWorkout: String,
    navigateToWorkout: (
        workout: Workout
    ) -> Unit,
    onSave: () -> Unit,
    placeholderDescription: String,
    placeholderName: String
) = NewWorkoutScreenProps(
    labelDescription,
    labelName,
    labelNewWorkout,
    navigateToWorkout,
    onSave,
    placeholderDescription,
    placeholderName
)

@Composable
internal fun NewWorkoutScreen(
    props: NewWorkoutScreenProps,
    state: NewWorkoutState,
    stateFields: NewWorkoutStateFields,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Text(
            props.labelNewWorkout,
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().headlineLarge,
            textAlign = TextAlign.Center
        )

        TextField(
            KeyboardType.Text,
            props.labelName,
            stateFields::updateName,
            props.placeholderName,
            stateFields.name,
            Modifier
                .fillMaxWidth(),
            capitalization = KeyboardCapitalization.Words
        )

        TextField(
            KeyboardType.Text,
            props.labelDescription,
            stateFields::updateDescription,
            props.placeholderDescription,
            stateFields.description,
            Modifier
                .fillMaxWidth(),
            capitalization = KeyboardCapitalization.Sentences,
            onDone = {
                props.onSave()
            }
        )

        when (state) {
            is NewWorkoutState.Error -> {
                Text(
                    state.throwable.message
                        ?: "Error",
                    Modifier
                        .weight(1f),
                    color = colors().error,
                    style = typography().titleSmall
                )
            }

            NewWorkoutState.Loading -> {
                Box(
                    Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Loading()
                }
            }

            is NewWorkoutState.Success -> {
                state.exerciseExecutions.forEach {
                    Text(
                        "name: ${it.name} - id: ${it.id}",
                        Modifier
                            .fillMaxWidth(),
                        color = colors().onBackground,
                        style = typography().titleMedium
                    )
                }
            }

            is NewWorkoutState.SuccessNewWorkout -> {
                props.navigateToWorkout(state.workout)
            }
        }
    }
}

internal val newWorkoutStateValuesProvider = sequenceOf(
    NewWorkoutState.Loading,
    NewWorkoutState.Error(
        Throwable("Test error")
    ),
    NewWorkoutState.Success(listOf())
)
