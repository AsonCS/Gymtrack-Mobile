package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.WorkoutWithExerciseExecutionsLocal
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

interface WorkoutWithExerciseExecutionsRepository {

    class Impl(
        private val local: WorkoutWithExerciseExecutionsLocal
    ) : WorkoutWithExerciseExecutionsRepository {
        override suspend fun putWorkoutWithExerciseExecutions(
            exerciseExecution: ExerciseExecution,
            workout: Workout
        ): Wrapper<Unit> {
            return try {
                Wrapper.Success(
                    local.putWorkoutWithExerciseExecutions(
                        exerciseExecution,
                        workout
                    )
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "WorkoutWithExerciseExecutionsRepository.local.putWorkoutWithExerciseExecutions",
                    t
                )
                Wrapper.Error(t)
            }
        }
    }

    suspend fun putWorkoutWithExerciseExecutions(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    ): Wrapper<Unit>

}
