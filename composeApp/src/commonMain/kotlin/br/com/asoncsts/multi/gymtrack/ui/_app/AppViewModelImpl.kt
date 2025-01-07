package br.com.asoncsts.multi.gymtrack.ui._app

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import kotlinx.coroutines.flow.*

class AppViewModelImpl(
    private val exerciseRepo: ExerciseRepository
) : AppViewModel() {

    private val _stateAuth = MutableStateFlow<AuthState>(
        AuthState.Unknown
    )
    override val stateAuth = _stateAuth
        .asStateFlow()

    private val _stateExercises = MutableStateFlow<ExercisesState>(
        ExercisesState.Loading
    )
    override val stateExercises = _stateExercises
        .asStateFlow()

    override fun stateAuthUpdate(
        state: AuthState
    ) {
        _stateAuth.update {
            state
        }
    }

    override suspend fun getExercises() {
        if (_stateExercises.value is ExercisesState.Success) return

        when (val result = exerciseRepo.getExercises()) {
            is Wrapper.Error -> {
                _stateExercises.update {
                    ExercisesState.Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                _stateExercises.update {
                    ExercisesState.Success(
                        result.data
                    )
                }
            }
        }
    }

}
