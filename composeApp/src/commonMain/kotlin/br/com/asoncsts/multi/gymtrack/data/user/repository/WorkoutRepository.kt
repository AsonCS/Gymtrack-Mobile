package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._exceptions.EmptyException
import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutLocal
import br.com.asoncsts.multi.gymtrack.data.user.remote.WorkoutRemote
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.workout.Workout

interface WorkoutRepository {

    class Impl(
        private val local: WorkoutLocal,
        private val remote: WorkoutRemote,
    ) : WorkoutRepository {

        override suspend fun getWorkouts(): Wrapper<List<Workout>> {
            val localResult = try {
                local.getWorkouts()
            } catch (t: Throwable) {
                TAG_DATA.error("WorkoutRepository.local.workoutDao", t)
                listOf()
            }
            val remoteResult = try {
                remote.getWorkouts()
            } catch (t: Throwable) {
                //TAG_DATA.error("WorkoutRepository.remote.getWorkouts", t)
                listOf()
            }
            val result = localResult + remoteResult

            return when {
                result.isEmpty() -> Wrapper.Error(
                    EmptyException()
                )

                else -> Wrapper.Success(
                    result
                )
            }
        }

        override suspend fun putWorkout(
            workout: Workout
        ): Wrapper<Workout> {
            return try {
                Wrapper.Success(
                    remote.putWorkout(
                        workout
                    )
                )
            } catch (t: Throwable) {
                TAG_DATA.error("WorkoutRepository.putWorkout", t)
                Wrapper.Error(t)
            }
        }

    }

    suspend fun getWorkouts(): Wrapper<List<Workout>>

    suspend fun putWorkout(
        workout: Workout
    ): Wrapper<Workout>

}
