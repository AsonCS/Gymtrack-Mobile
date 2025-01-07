package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.dao.WorkoutDao
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.WorkoutEntity
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface WorkoutLocal {

    class Impl(
        private val dao: WorkoutDao
    ) : WorkoutLocal {

        override suspend fun getWorkouts(): Flow<List<Workout>> {
            return dao.getWorkoutsWithExerciseExecutions()
                .map { list ->
                    list.map { it.toWorkout() }
                }
        }

        override suspend fun putWorkout(
            workout: Workout
        ): Workout {
            val entity = WorkoutEntity(
                workout
            )
            dao.insert(entity)
            return workout.copy(
                id = entity.workoutId
            )
        }

    }

    suspend fun getWorkouts(): Flow<List<Workout>>

    suspend fun putWorkout(
        workout: Workout
    ): Workout

}
