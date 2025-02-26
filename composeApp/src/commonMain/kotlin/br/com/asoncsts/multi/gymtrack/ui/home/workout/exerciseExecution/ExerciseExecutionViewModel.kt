package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution.Companion.fillExercise
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.execution.StateFields
import kotlinx.coroutines.flow.*

abstract class ExerciseExecutionViewModel : ViewModel() {
    class Impl(
        private val exercisesSource: ExercisesSource,
        private val repo: ExerciseExecutionRepository
    ) : ExerciseExecutionViewModel() {
        private val _state = MutableStateFlow<ExerciseExecutionState>(
            ExerciseExecutionState.Loading
        )
        override val state = _state.asStateFlow()

        private val _stateFields: MutableStateFlow<StateFields> by lazy {
            MutableStateFlow(
                StateFields { update ->
                    _stateFields.update { currentState ->
                        update(currentState)
                    }
                }
            )
        }
        override val stateFields = _stateFields.asStateFlow()

        override suspend fun getExerciseExecution(
            id: String
        ) {
            val result = repo.getExerciseExecution(id)
            when (result) {
                is Wrapper.Error -> {
                    _state.update {
                        ExerciseExecutionState.Error(
                            result.error
                        )
                    }
                }

                is Wrapper.Success -> {
                    result.data.collect { exerciseExecution ->
                        _state.update {
                            ExerciseExecutionState.Success(
                                exerciseExecution.fillExercise(
                                    exercisesSource::getExercise
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    internal abstract val state: StateFlow<ExerciseExecutionState>
    internal abstract val stateFields: StateFlow<StateFields>

    internal abstract suspend fun getExerciseExecution(
        id: String
    )
}
