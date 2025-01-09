package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.dao.WorkoutWithExerciseExecutionsDao
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.WorkoutWithExerciseExecutionsCrossRefEntity
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

interface WorkoutWithExerciseExecutionsLocal {

    class Impl(
        private val dao: WorkoutWithExerciseExecutionsDao
    ) : WorkoutWithExerciseExecutionsLocal {
        override suspend fun putWorkoutWithExerciseExecutions(
            exerciseExecution: ExerciseExecution,
            workout: Workout
        ) {
            dao.insert(
                WorkoutWithExerciseExecutionsCrossRefEntity(
                    exerciseExecution,
                    workout
                )
            )
        }
    }

    suspend fun putWorkoutWithExerciseExecutions(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    )

}
