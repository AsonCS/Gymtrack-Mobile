package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepository
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState
import br.com.asoncsts.multi.gymtrack.di.koinApplication
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.ui._theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

const val TAG_APP = "gymtrack."

@Composable
fun App(
    modifier: Modifier = Modifier,
    platformModule: Module = module {}
) {
    KoinApplication({
        koinApplication(
            platformModule
        )
    }) {
        AppTheme {
            val appViewModel = koinViewModel<AppViewModel>()
            val auth = koinInject<AuthRepository>()

            AppScreen(
                appViewModel,
                modifier
            )

            LaunchedEffect(Unit) {
                delay(1_000)
                appViewModel.launch {
                    getExercises()
                }
                auth.onAuthInit(appViewModel::stateAuthUpdate)
            }
        }
    }
}

abstract class AppViewModel : ViewModel() {
    abstract val stateAuth: StateFlow<AuthState>
    abstract val stateExercises: StateFlow<ExercisesState>

    abstract suspend fun getExercises()

    fun getExercise(
        alias: String
    ): Exercise {
        return (stateExercises.value as? ExercisesState.Success)
            ?.exercises
            ?.find { it.alias == alias }
            ?: throw IllegalStateException("Exercise not found")
    }

    abstract fun stateAuthUpdate(
        state: AuthState
    )
}
