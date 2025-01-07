package br.com.asoncsts.multi.gymtrack.ui.home.newWorkout

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.ui.BackHandlerContainer
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.NewWorkoutArgs
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.components.NewWorkoutScreen
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.components.newWorkoutScreenProps
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewWorkoutScreen(
    args: NewWorkoutArgs,
    modifier: Modifier = Modifier,
    viewModel: NewWorkoutViewModel = koinViewModel()
) {
    val state by viewModel
        .state
        .collectAsState()
    val stateFields by viewModel
        .stateFields
        .collectAsState()

    BackHandlerContainer(
        args.navigateUp,
        modifier
    ) {
        NewWorkoutScreen(
            newWorkoutScreenProps(
                navigateToWorkout = args.navigateToWorkout,
                onSave = {
                    viewModel.launch {
                        save()
                    }
                }
            ),
            state,
            stateFields,
            modifier
        )
    }

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

    internal abstract suspend fun save()
}
