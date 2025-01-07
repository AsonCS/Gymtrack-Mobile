package br.com.asoncsts.multi.gymtrack.ui.home.workout

import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.repository.ExerciseExecutionRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui._app.ExercisesSource
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState.*
import kotlinx.coroutines.flow.*

class WorkoutViewModelImpl(
    private val exercisesSource: ExercisesSource,
    private val repo: ExerciseExecutionRepository
) : WorkoutViewModel() {

    private val _state = MutableStateFlow<WorkoutState>(Loading)
    override val state = _state.asStateFlow()

    override suspend fun getWorkout(
        workout: Workout
    ) {
        if (_state.value is Success) return

        val result = repo.getExerciseExecutions(
            workout.exerciseExecutionIds
        )
        when (result) {
            is Wrapper.Error -> {
                _state.update {
                    Error(
                        result.error
                    )
                }
            }

            is Wrapper.Success -> {
                result.data.collect {
                    collect(it)
                }
            }
        }
    }

    private fun collect(exercisesExecutions: List<ExerciseExecution>) {
        val result = exercisesExecutions.map { exercisesExecution ->
            val result = exercisesSource.getExercise(
                exercisesExecution.exercise.alias
            )
            val exercise = when (result) {
                is Wrapper.Error -> {
                    Exercise.Impl(
                        alias = exercisesExecution.exercise.alias,
                        title = "Error"
                    )
                }

                is Wrapper.Success -> {
                    result.data
                }
            }

            ExerciseExecution.Impl(
                exercise = exercise,
                id = exercisesExecution.id,
                name = exercisesExecution.name
            )
        }

        _state.update {
            Success(
                result
            )
        }
    }
}
