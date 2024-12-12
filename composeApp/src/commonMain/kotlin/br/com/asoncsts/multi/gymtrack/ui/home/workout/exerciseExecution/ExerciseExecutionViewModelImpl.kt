package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution.ExerciseExecutionState.*
import kotlinx.coroutines.flow.*

class ExerciseExecutionViewModelImpl(
    private val repo: ExerciseExecutionRepository
) : ExerciseExecutionViewModel() {

    private val _state = MutableStateFlow<ExerciseExecutionState>(Loading)
    override val state = _state.asStateFlow()

    override suspend fun getExerciseExecution(
        id: String
    ) {
        when (val result = repo.getExerciseExecution(id)) {
            is Wrapper.Error -> {
                _state.update {
                    Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                _state.update {
                    Success(
                        result.data
                    )
                }
            }
        }
    }

}
