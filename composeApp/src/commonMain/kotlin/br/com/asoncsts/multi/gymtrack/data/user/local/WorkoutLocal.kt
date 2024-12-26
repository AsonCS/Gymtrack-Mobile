package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutDao
import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutEntity
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface WorkoutLocal {

    class Impl(
        private val workoutDao: WorkoutDao
    ) : WorkoutLocal {

        override suspend fun getWorkouts(): Flow<List<Workout>> {
            return workoutDao.getWorkoutsWithExerciseExecutions()
                .map { list ->
                    list.map { it.toWorkout() }
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

    suspend fun getWorkouts(): Flow<List<Workout>>

    suspend fun putWorkout(
        workout: Workout
    ): Workout

}
