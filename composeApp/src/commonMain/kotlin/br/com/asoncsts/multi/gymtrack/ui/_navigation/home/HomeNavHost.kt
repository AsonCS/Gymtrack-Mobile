package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeNavArgs
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

sealed class HomeNavDestination<Args>(
    val route: String
) {
    abstract operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    )
}

@Composable
fun HomeNavHost(
    args: HomeNavArgs,
    destination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModelExerciseExecution: ExerciseExecutionViewModel = koinViewModel {
        parametersOf(
            args.exercisesSource
        )
    },
    viewModelWorkout: WorkoutViewModel = koinViewModel {
        parametersOf(
            args.exercisesSource
        )
    },
) {
    NavHost(
        navController = navController,
        startDestination = destination,
        modifier = modifier
    ) {
        ExerciseExecutionDestination(
            ExerciseExecutionDestination.Args(
                navigateUp = navController::navigateUp,
                viewModelExerciseExecution
            ),
            this
        )
        newExerciseExecution(
            NewExerciseExecutionArgs(
                exercisesSource = args.exercisesSource,
                navigateUp = navController::navigateUp
            )
        )
        workout(
            WorkoutArgs(
                navController::navigateToExerciseExecution,
                navController::navigateToNewExerciseExecution,
                args.navigateUp,
                viewModelWorkout,
                workout = {
                    args.viewModel
                        .navigationArgumentWorkout
                }
            )
        )
    }
}

fun NavHostController.navigateToExerciseExecution(
    id: String
) {
    navigate(ExerciseExecutionDestination.route(id))
}
