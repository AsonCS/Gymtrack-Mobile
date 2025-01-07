package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.WorkoutLocal
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    class Impl(
        private val local: WorkoutLocal
    ) : WorkoutRepository {

        override suspend fun getWorkouts(): Wrapper<Flow<List<Workout>>> {
            return try {
                Wrapper.Success(
                    local.getWorkouts()
                )
            } catch (t: Throwable) {
                TAG_DATA.error("WorkoutRepository.local.getWorkouts", t)
                Wrapper.Error(t)
            }
        }

        override suspend fun putWorkout(
            workout: Workout
        ): Wrapper<Workout> {
            return try {
                Wrapper.Success(
                    local.putWorkout(workout)
                )
            } catch (t: Throwable) {
                TAG_DATA.error("WorkoutRepository.local.putWorkout", t)
                Wrapper.Error(t)
            }
        }

    }

    suspend fun getWorkouts(): Wrapper<Flow<List<Workout>>>

    suspend fun putWorkout(
        workout: Workout
    ): Wrapper<Workout>

}
