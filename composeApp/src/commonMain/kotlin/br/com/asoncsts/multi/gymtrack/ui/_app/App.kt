package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
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
                    loadExercises()
                }
                auth.onAuthInit(appViewModel::stateAuthUpdate)
            }
        }
    }
}

abstract class AppViewModel : ViewModel(), ExercisesSource {
    abstract val stateAuth: StateFlow<AuthState>
    abstract val stateExercises: StateFlow<ExercisesState>

    abstract suspend fun loadExercises()

    abstract fun stateAuthUpdate(
        state: AuthState
    )

    override fun getExercise(
        alias: String
    ): Wrapper<Exercise> {
        val result = getExercises()
        return when (result) {
            is Wrapper.Error -> Wrapper.Error(
                result.error
            )

            is Wrapper.Success -> {
                val exercise = result.data
                    .find { it.alias == alias }

                if (exercise != null)
                    Wrapper.Success(exercise)
                else
                    Wrapper.Error(
                        IllegalStateException("Exercise not found")
                    )
            }
        }
    }

    override fun getExercises(): Wrapper<List<Exercise>> {
        val exercises = (stateExercises.value as? ExercisesState.Success)
            ?.exercises

        return if (exercises != null)
            Wrapper.Success(exercises)
        else
            Wrapper.Error(IllegalStateException("Exercises not found"))
    }
}

interface ExercisesSource {
    fun getExercise(
        alias: String
    ): Wrapper<Exercise>

    fun getExercises(): Wrapper<List<Exercise>>
}
