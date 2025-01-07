package br.com.asoncsts.multi.gymtrack.ui._navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeNavDestination.Args
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
    args: Args,
    destination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModelExerciseExecution: ExerciseExecutionViewModel = koinViewModel(),
    viewModelWorkout: WorkoutViewModel = koinViewModel {
        parametersOf(
            args.getExercise
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
        NewWorkoutDestination(
            NewWorkoutDestination.Args(
                navigateToWorkout = {
                    args.viewModel
                        .navigationArgumentWorkout = it
                    navController.navigateToWorkout()
                },
                navigateUp = args.navigateUp
            ),
            this
        )
        WorkoutDestination(
            WorkoutDestination.Args(
                navController::navigateToExerciseExecution,
                navController::navigateToNewExerciseExecution,
                navigateUp = navController::navigateUp,
                viewModelWorkout,
                workout = {
                    args.viewModel
                        .navigationArgumentWorkout
                }
            ),
            this
        )
    }
}

fun NavHostController.navigateToExerciseExecution(
    id: String
) {
    navigate(ExerciseExecutionDestination.route(id))
}

fun NavHostController.navigateToNewExerciseExecution() {
    // navigate(NewExerciseExecutionDestination.route(id))
}

fun NavHostController.navigateToWorkout() {
    navigate(WorkoutDestination.route) {
        popUpTo(NewWorkoutDestination.route) {
            inclusive = true
        }
    }
}
