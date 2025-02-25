package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution.Companion.fillExercise
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import kotlinx.coroutines.flow.*

class ExerciseExecutionViewModelImpl(
    private val exercisesSource: ExercisesSource,
    private val repo: ExerciseExecutionRepository
) : ExerciseExecutionViewModel() {

    private val _state = MutableStateFlow<ExerciseExecutionState>(
        ExerciseExecutionState.Loading
    )
    override val state = _state.asStateFlow()

    override suspend fun getExerciseExecution(
        id: String
    ) {
        when (val result = repo.getExerciseExecution(id)) {
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
