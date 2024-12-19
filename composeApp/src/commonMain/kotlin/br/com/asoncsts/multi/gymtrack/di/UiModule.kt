package br.com.asoncsts.multi.gymtrack.di

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
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutViewModel
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModel
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModelImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal fun uiModule() = module {
    viewModel<AppViewModel> {
        AppViewModelImpl()
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
            repo = get()
        )
    }
    viewModel<SearchViewModel> {
        SearchViewModelImpl(
            repo = get()
        )
    }
    viewModel<WorkoutViewModel> {
        WorkoutViewModelImpl(
            repo = get()
        )
    }
}
