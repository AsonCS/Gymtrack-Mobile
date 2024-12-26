package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutDao
import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutEntity
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

interface WorkoutLocal {

    class Impl(
        private val workoutDao: WorkoutDao
    ) : WorkoutLocal {

        override suspend fun getWorkouts(): List<Workout> {
            val result = workoutDao.getWorkoutsWithExerciseExecutions()

            return when {
                result.isEmpty() -> throw EmptyException()

                else -> result
                    .map { it.toWorkout() }
            }
        }

        override suspend fun putWorkout(
            workout: Workout
        ): Workout {
            workoutDao.insert(
                WorkoutEntity(
                    workout
                )
            )
            return workout
        }

    }

    suspend fun getWorkouts(): List<Workout>

    suspend fun putWorkout(
        workout: Workout
    ): Workout

}