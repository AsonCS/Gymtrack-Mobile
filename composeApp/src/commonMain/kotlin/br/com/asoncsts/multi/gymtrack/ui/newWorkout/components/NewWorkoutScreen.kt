package br.com.asoncsts.multi.gymtrack.ui.newWorkout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._components.TextField
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutState
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutStateFields

internal data class NewWorkoutScreenProps(
    val labelDescription: String,
    val labelName: String,
    val labelNewWorkout: String,
    val onSave: () -> Unit,
    val placeholderDescription: String,
    val placeholderName: String
)

@Composable
internal fun newWorkoutScreenProps(
    labelDescription: String,
    labelName: String,
    labelNewWorkout: String,
    onSave: () -> Unit,
    placeholderDescription: String,
    placeholderName: String
) = NewWorkoutScreenProps(
    labelDescription,
    labelName,
    labelNewWorkout,
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
            keyboardType = KeyboardType.Text,
            label = props.labelName,
            onValueChange = stateFields::updateName,
            placeholder = props.placeholderName,
            value = stateFields.name,
            Modifier
                .fillMaxWidth()
        )

        TextField(
            keyboardType = KeyboardType.Text,
            label = props.labelDescription,
            onValueChange = stateFields::updateDescription,
            placeholder = props.placeholderDescription,
            value = stateFields.description,
            Modifier
                .fillMaxWidth(),
            onDone = {
                props.onSave()
            }
        )

        if (state is NewWorkoutState.Success) {
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
    }
}

internal val newWorkoutStateValuesProvider = sequenceOf(
    NewWorkoutState.Loading,
    NewWorkoutState.Error(
        Throwable("Test error")
    ),
    NewWorkoutState.Success(listOf())
)
