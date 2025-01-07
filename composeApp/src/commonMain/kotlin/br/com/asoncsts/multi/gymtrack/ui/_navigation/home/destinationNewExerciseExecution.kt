package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.NewExerciseExecutionScreen

private const val newExerciseExecutionRoute = "new_exercise_execution"

class NewExerciseExecutionArgs(
    val exercisesSource: ExercisesSource,
    val navigateUp: () -> Unit
)

fun NavGraphBuilder.newExerciseExecution(
    args: NewExerciseExecutionArgs
) {
    composable(newExerciseExecutionRoute) {
        NewExerciseExecutionScreen(args)
    }
}

fun NavHostController.navigateToNewExerciseExecution() {
    navigate(newExerciseExecutionRoute)
}
