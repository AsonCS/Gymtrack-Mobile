package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.NewWorkoutViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.newWorkout.NewWorkoutViewModelImpl
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
    viewModel<ExerciseExecutionViewModel> {
        ExerciseExecutionViewModelImpl(
            repo = get()
        )
    }
    viewModel<HomeViewModel> {
        HomeViewModelImpl(
            repo = get()
        )
    }
    viewModel<NewWorkoutViewModel> {
        NewWorkoutViewModelImpl(
            exerciseExecutionRepo = get(),
            workoutRepo = get()
        )
    }
    viewModel<WorkoutViewModel> { (getExercise: (alias: String) -> Exercise) ->
        WorkoutViewModelImpl(
            getExercise = getExercise,
            repo = get()
        )
    }
}
