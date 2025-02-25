package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.ui._app.*
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.NewExerciseExecutionViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.NewExerciseExecutionViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.NewWorkoutViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.NewWorkoutViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionViewModelImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal fun uiModule() = module {
    viewModel<AppViewModel> {
        AppViewModelImpl(
            exerciseRepo = get()
        )
    }
    viewModel<AuthViewModel> {
        AuthViewModelImpl(
            auth = get()
        )
    }
    viewModel<ExerciseExecutionViewModel> { (exercisesSource: ExercisesSource) ->
        ExerciseExecutionViewModelImpl(
            exercisesSource = exercisesSource,
            repo = get()
        )
    }
    viewModel<HomeViewModel> {
        HomeViewModelImpl(
            repo = get()
        )
    }
    viewModel<NewExerciseExecutionViewModel> { (exercisesSource: ExercisesSource) ->
        NewExerciseExecutionViewModelImpl(
            exerciseExecutionRepo = get(),
            exercisesSource = exercisesSource
        )
    }
    viewModel<NewWorkoutViewModel> {
        NewWorkoutViewModelImpl(
            exerciseExecutionRepo = get(),
            workoutRepo = get()
        )
    }
    viewModel<WorkoutViewModel> { (exercisesSource: ExercisesSource) ->
        WorkoutViewModelImpl(
            exerciseExecutionRepo = get(),
            exercisesSource = exercisesSource,
            workoutWithExerciseExecutionsRepo = get()
        )
    }
}
