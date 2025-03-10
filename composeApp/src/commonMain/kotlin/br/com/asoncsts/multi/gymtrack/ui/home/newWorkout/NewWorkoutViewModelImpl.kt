package br.com.asoncsts.multi.gymtrack.ui.home.newWorkout

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutRepository
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.coroutines.flow.*

class NewWorkoutViewModelImpl(
    private val exerciseExecutionRepo: ExerciseExecutionRepository,
    private val workoutRepo: WorkoutRepository
) : NewWorkoutViewModel() {

    private val _state = MutableStateFlow<NewWorkoutState>(
        NewWorkoutState.Loading
    )
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
        val result = exerciseExecutionRepo.getExerciseExecutions()
        when (result) {
            is Wrapper.Error -> {
                _state.update {
                    NewWorkoutState.Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                result.data.collect { exerciseExecutions ->
                    _state.update {
                        NewWorkoutState.Success(
                            exerciseExecutions
                        )
                    }
                }
            }
        }
    }

    override suspend fun save() {
        val description = stateFields
            .value
            .description
        val name = stateFields
            .value
            .name
            ?: throw IllegalStateException("Name cannot be empty")

        val result = workoutRepo.putWorkout(
            Workout(
                description = description,
                exerciseExecutionIds = listOf(),
                name = name
            )
        )

        when (result) {
            is Wrapper.Error -> {
                _state.update {
                    NewWorkoutState.Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                _state.update {
                    NewWorkoutState.SuccessNewWorkout(
                        result.data
                    )
                }
            }
        }
    }

}
