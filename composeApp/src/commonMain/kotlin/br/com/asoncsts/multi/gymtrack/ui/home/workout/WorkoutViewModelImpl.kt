package br.com.asoncsts.multi.gymtrack.ui.home.workout

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutRepository
import br.com.asoncsts.multi.gymtrack.extension.log
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution.Companion.fillExercise
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import kotlinx.coroutines.flow.*

class WorkoutViewModelImpl(
    private val exerciseExecutionRepo: ExerciseExecutionRepository,
    private val exercisesSource: ExercisesSource,
    private val workoutRepo: WorkoutRepository
) : WorkoutViewModel() {

    private val _shared = MutableSharedFlow<WorkoutShared>()
    override val shared = _shared.asSharedFlow()

    private val _state = MutableStateFlow<WorkoutState>(
        WorkoutState.Loading
    )
    override val state = _state.asStateFlow()

    override suspend fun addNewExerciseExecution(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    ) {
        val result = workoutRepo.addExerciseExecution(
            exerciseExecution,
            workout
        )

        when (result) {
            is Wrapper.Error -> {
                _shared.emit(
                    WorkoutShared.ErrorAddNewExerciseExecution(
                        result.error.message
                            ?: "Error"
                    )
                )
            }

            is Wrapper.Success -> {
                _state.update {
                    it as WorkoutState.Success
                    it.copy(
                        filteredExerciseExecutions = it
                            .filteredExerciseExecutions + exerciseExecution
                    )
                }
                _shared.emit(
                    WorkoutShared.SuccessAddNewExerciseExecution
                )
            }
        }
    }

    override suspend fun clearExerciseExecution(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    ) {
        val result = workoutRepo.removeExerciseExecution(
            exerciseExecution,
            workout
        )

        when (result) {
            is Wrapper.Error -> {
                _shared.emit(
                    WorkoutShared.ErrorClearExerciseExecution(
                        result.error.message
                            ?: "Error"
                    )
                )
            }

            is Wrapper.Success -> {
                _state.update {
                    it as WorkoutState.Success
                    it.copy(
                        filteredExerciseExecutions = it
                            .filteredExerciseExecutions - exerciseExecution
                    )
                }
                _shared.emit(
                    WorkoutShared.SuccessClearExerciseExecution
                )
            }
        }
    }

    override suspend fun getWorkout(
        workout: Workout
    ) {
        if (_state.value is WorkoutState.Success) return

        val result = exerciseExecutionRepo.getExerciseExecutions()
        when (result) {
            is Wrapper.Error -> {
                _shared.emit(
                    WorkoutShared.ErrorInit(
                        result.error.message
                            ?: "Error"
                    )
                )
            }

            is Wrapper.Success -> {
                result.data.collect { exerciseExecutions ->
                    "fatal".log("WorkoutViewModelImpl.getWorkout.Success: $exerciseExecutions")
                    val filtered = mutableListOf<ExerciseExecution>()
                    val filled = exerciseExecutions.fillExercise(
                        exercisesSource::getExercise
                    ) {
                        if (workout.exerciseExecutionIds.contains(it.id)) {
                            filtered.add(it)
                        }
                    }

                    _state.update {
                        WorkoutState.Success(
                            filled,
                            filtered
                        )
                    }
                }
            }
        }
    }

}
