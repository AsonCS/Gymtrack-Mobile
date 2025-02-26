package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    val stateFields by args.viewModel
        .stateFields
        .collectAsState()

    ExerciseExecutionScreen(
        props(
            args.navigateUp
        ),
        state,
        stateFields,
        modifier
    )

    LaunchedEffect(Unit) {
        args.viewModel.getExerciseExecution(exerciseExecutionId)
    }
}
