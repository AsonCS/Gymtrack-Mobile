package br.com.asoncsts.multi.gymtrack.ui.newWorkout

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.ui._navigation.NewWorkoutDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.components.NewWorkoutScreen
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.components.newWorkoutScreenProps
import gymtrack.composeapp.generated.resources.*
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewWorkoutScreen(
    args: Args,
    modifier: Modifier = Modifier,
    viewModel: NewWorkoutViewModel = koinViewModel()
) {
    val state by viewModel
        .state
        .collectAsState()
    val stateFields by viewModel
        .stateFields
        .collectAsState()

    NewWorkoutScreen(
        newWorkoutScreenProps(
            labelDescription = stringResource(
                Res.string.new_workout_label_description
            ),
            labelName = stringResource(
                Res.string.new_workout_label_name
            ),
            labelNewWorkout = stringResource(
                Res.string.new_workout_label_new_workout
            ),
            placeholderDescription = stringResource(
                Res.string.new_workout_placeholder_description
            ),
            placeholderName = stringResource(
                Res.string.new_workout_placeholder_name
            )
        ),
        state,
        stateFields,
        modifier
    )

    LaunchedEffect(Unit) {
        viewModel.launch {
            getExerciseExecutions()
        }
    }
}

abstract class NewWorkoutViewModel : ViewModel() {
    internal abstract val state: StateFlow<NewWorkoutState>
    internal abstract val stateFields: StateFlow<NewWorkoutStateFields>

    internal abstract suspend fun getExerciseExecutions()
}
