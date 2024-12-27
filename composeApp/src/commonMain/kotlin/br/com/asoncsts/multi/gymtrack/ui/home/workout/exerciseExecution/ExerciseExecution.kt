package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.ui.BackHandlerContainer
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.ExerciseExecutionDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.components.ExerciseExecutionScreen
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ExerciseExecutionScreen(
    args: Args,
    exerciseExecutionId: String,
    modifier: Modifier = Modifier
) {
    val state by args.viewModel
        .state
        .collectAsState()

    BackHandlerContainer(
        args.navigateUp,
        modifier
    ) {
        ExerciseExecutionScreen(
            state,
            modifier
        )
    }

    LaunchedEffect(Unit) {
        args.viewModel.getExerciseExecution(exerciseExecutionId)
    }
}

abstract class ExerciseExecutionViewModel : ViewModel() {
    internal abstract val state: StateFlow<ExerciseExecutionState>

    internal abstract suspend fun getExerciseExecution(
        id: String
    )
}
