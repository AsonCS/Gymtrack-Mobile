package br.com.asoncsts.multi.gymtrack.ui.home.workout

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutWithExerciseExecutionsRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution.Companion.fillExercise
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import kotlinx.coroutines.flow.*

class WorkoutViewModelImpl(
    private val exerciseExecutionRepo: ExerciseExecutionRepository,
    private val exercisesSource: ExercisesSource,
    private val workoutWithExerciseExecutionsRepo: WorkoutWithExerciseExecutionsRepository
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
        val result = workoutWithExerciseExecutionsRepo.putWorkoutWithExerciseExecutions(
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
                _shared.emit(
                    WorkoutShared.SuccessAddNewExerciseExecution
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
