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
    exerciseExecutionViewModel: ExerciseExecutionViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController(),
    workoutViewModel: WorkoutViewModel = koinViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = destination,
        modifier = modifier
    ) {
        ExerciseExecutionDestination(
            ExerciseExecutionDestination.Args(
                exerciseExecutionViewModel
            ),
            this
        )
        NewWorkoutDestination(
            NewWorkoutDestination.Args(
                navigateToWorkout = {
                    args.homeViewModel
                        .navigationArgumentWorkout = it
                    navController.navigateToWorkout()
                }
            ),
            this
        )
        WorkoutDestination(
            WorkoutDestination.Args(
                navController::navigateToExerciseExecution,
                navController::navigateToNewExerciseExecution,
                workoutViewModel,
                workout = {
                    args.homeViewModel
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
    //navigate(NewExerciseExecutionDestination.route(id))
}

fun NavHostController.navigateToWorkout() {
    navigate(WorkoutDestination.route) {
        popUpTo(NewWorkoutDestination.route) {
            inclusive = true
        }
    }
}
