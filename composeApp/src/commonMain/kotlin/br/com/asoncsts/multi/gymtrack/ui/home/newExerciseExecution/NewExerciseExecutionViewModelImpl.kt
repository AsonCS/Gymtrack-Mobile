package br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class NewExerciseExecutionViewModelImpl(
    private val exerciseExecutionRepo: ExerciseExecutionRepository,
    private val exercisesSource: ExercisesSource,
) : NewExerciseExecutionViewModel() {

    private val _state = MutableStateFlow<NewExerciseExecutionState>(
        NewExerciseExecutionState.Loading
    )
    override val state = _state

    private val _stateFields: MutableStateFlow<NewExerciseExecutionStateFields> by lazy {
        MutableStateFlow(
            NewExerciseExecutionStateFields { update ->
                _stateFields.update { currentState ->
                    update(currentState)
                }
            }
        )
    }
    override val stateFields = _stateFields

    override suspend fun getExercises() {
        val result = exercisesSource.getExercises()
        when (result) {
            is Wrapper.Error -> {
                _state.update {
                    NewExerciseExecutionState.Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                _state.update {
                    NewExerciseExecutionState.Success(
                        result.data
                    )
                }
            }
        }
    }

    override suspend fun save() {
        val description = stateFields
            .value
            .description
        val exercise = stateFields
            .value
            .exercise
        val name = stateFields
            .value
            .name
            ?: throw IllegalStateException("Name cannot be empty")

        val result = exerciseExecutionRepo.putExerciseExecution(
            ExerciseExecution.Detail(
                description = description,
                exercise = exercise,
                name = name
            )
        )

        when (result) {
            is Wrapper.Error -> {
                _state.update {
                    NewExerciseExecutionState.Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                _state.update {
                    NewExerciseExecutionState.SuccessNewExerciseExecution(
                        result.data
                    )
                }
            }
        }
    }

}
