package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.ui._app.*
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.NewExerciseExecutionViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.NewExerciseExecutionViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionViewModel
import br.com.asoncsts.multi.gymtrack.ui.workout.WorkoutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModel as HomeWorkoutViewModel

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
        ExerciseExecutionViewModel.Impl(
            exercisesSource = exercisesSource,
            exerciseExecutionRepo = get(),
            executionRepo = get()
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
    viewModel<HomeWorkoutViewModel> { (exercisesSource: ExercisesSource) ->
        WorkoutViewModelImpl(
            exerciseExecutionRepo = get(),
            exercisesSource = exercisesSource,
            workoutRepo = get()
        )
    }
    viewModel<WorkoutViewModel> {
        WorkoutViewModel.Impl(
            workoutRepo = get()
        )
    }
}
