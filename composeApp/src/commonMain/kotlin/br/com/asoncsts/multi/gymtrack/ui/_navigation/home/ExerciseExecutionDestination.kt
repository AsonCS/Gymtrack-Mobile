package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.ExerciseExecutionDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionScreen
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionViewModel

data object ExerciseExecutionDestination : HomeNavDestination<Args>(
    "exercise_execution/{id}"
) {
    class Args(
        val navigateUp: () -> Unit,
        val viewModel: ExerciseExecutionViewModel
    )

    override fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            val id = it.arguments
                ?.getString("id")
                ?: throw IllegalStateException("Id is required")
            ExerciseExecutionScreen(args, id)
        }
    }


    fun route(
        id: String
    ) = "exercise_execution/$id"
}
