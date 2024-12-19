package br.com.asoncsts.multi.gymtrack.ui.newWorkout

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.ui.newWorkout.NewWorkoutState.*
import kotlinx.coroutines.flow.*

class NewWorkoutViewModelImpl(
    private val repo: ExerciseExecutionRepository
) : NewWorkoutViewModel() {

    private val _state = MutableStateFlow<NewWorkoutState>(Loading)
    override val state = _state.asStateFlow()

    private val _stateFields: MutableStateFlow<NewWorkoutStateFields> by lazy {
        MutableStateFlow(
            NewWorkoutStateFields { update ->
                _stateFields.update { currentState ->
                    update(currentState)
                }
            }
        )
    }
    override val stateFields = _stateFields.asStateFlow()

    override suspend fun getExerciseExecutions() {
        when (val result = repo.getExerciseExecutions()) {
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
