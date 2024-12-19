package br.com.asoncsts.multi.gymtrack.data.user.remote

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._exceptions.UnknownException
import br.com.asoncsts.multi.gymtrack.data.user.api.WorkoutApi
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

interface WorkoutRemote {

    class Impl(
        private val api: WorkoutApi
    ) : WorkoutRemote {

        override suspend fun getWorkouts(): List<Workout> {
            val result = api.getWorkouts()

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                result.data.isEmpty() -> throw EmptyException()

                else -> result.data
                    .map { it.toWorkout() }
            }
        }

        override suspend fun putWorkout(
            workout: Workout
        ): Workout {
            val result = api.putWorkout(workout)

            return when {
                result.data == null -> throw UnknownException(
                    result.error
                )

                else -> result.data.toWorkout()
            }
        }
    }

    suspend fun getWorkouts(): List<Workout>

    suspend fun putWorkout(
        workout: Workout
    ): Workout

}
