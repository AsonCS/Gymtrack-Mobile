package br.com.asoncsts.multi.gymtrack.ui.home.workout

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.data.user.repository.WorkoutWithExerciseExecutionsRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState.Loading
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState.Success
import kotlinx.coroutines.flow.*

class WorkoutViewModelImpl(
    private val exerciseExecutionRepo: ExerciseExecutionRepository,
    private val exercisesSource: ExercisesSource,
    private val workoutWithExerciseExecutionsRepo: WorkoutWithExerciseExecutionsRepository
) : WorkoutViewModel() {

    private val _shared = MutableSharedFlow<WorkoutShared>()
    override val shared = _shared.asSharedFlow()

    private val _state = MutableStateFlow<WorkoutState>(Loading)
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
        if (_state.value is Success) return

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
                result.data.collect {
                    collect(
                        workout.exerciseExecutionIds,
                        it
                    )
                }
            }
        }
    }

    private fun collect(
        exerciseExecutionIds: List<String>,
        exercisesExecutions: List<ExerciseExecution>
    ) {
        val all = mutableListOf<ExerciseExecution>()
        val filtered = mutableListOf<ExerciseExecution>()

        exercisesExecutions.forEach { exercisesExecution ->
            val alias = exercisesExecution.exercise
                ?.alias
            val result = alias
                ?.let {
                    exercisesSource.getExercise(it)
                }
            val exercise = when (result) {
                is Wrapper.Success -> {
                    result.data
                }

                else -> null
            }

            val exerciseExecution = ExerciseExecution.Impl(
                exercise,
                exercisesExecution.id,
                exercisesExecution.name
            )

            all.add(exerciseExecution)
            if (exerciseExecutionIds.contains(exerciseExecution.id)) {
                filtered.add(exerciseExecution)
            }
        }

        _state.update {
            Success(
                all,
                filtered
            )
        }
    }
}
