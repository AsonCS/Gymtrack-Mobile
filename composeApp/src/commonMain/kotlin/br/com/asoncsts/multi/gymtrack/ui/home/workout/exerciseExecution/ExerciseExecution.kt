package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.ui.Toast
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.ExerciseExecutionDestination.Args

@Composable
fun ExerciseExecutionScreen(
    args: Args,
    exerciseExecutionId: String,
    modifier: Modifier = Modifier
) {
    val state by args.viewModel
        .state
        .collectAsState()
    val shared by args.viewModel
        .shared
        .collectAsState(null)
    val stateFields by args.viewModel
        .stateFields
        .collectAsState()

    (shared as? ExerciseExecutionShared.ErrorOnEditExecution)
        ?.let {
            Toast(it.message)
        }

    ExerciseExecutionScreen(
        onExecutionAddOrUpdate = { execution ->
            args.viewModel.launch {
                onExecutionAddOrUpdate(execution)
            }
        },
        onExecutionConfirmChange = {
            args.viewModel.launch {
                onExecutionConfirmChange()
            }
        },
        onExecutionRemove = { executionId ->
            args.viewModel.launch {
                onExecutionRemove(executionId)
            }
        },
        args.navigateUp,
        state,
        stateFields,
        modifier
    )

    LaunchedEffect(Unit) {
        args.viewModel.getExerciseExecution(exerciseExecutionId)
    }
}
