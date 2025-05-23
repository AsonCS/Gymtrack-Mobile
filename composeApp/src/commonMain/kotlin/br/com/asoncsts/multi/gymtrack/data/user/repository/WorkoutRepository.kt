package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.WorkoutLocal
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    class Impl(
        private val local: WorkoutLocal
    ) : WorkoutRepository {

        override suspend fun addExerciseExecution(
            exerciseExecution: ExerciseExecution,
            workout: Workout
        ): Wrapper<Unit> {
            return try {
                Wrapper.Success(
                    local.addExerciseExecution(
                        exerciseExecution,
                        workout
                    )
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "WorkoutRepository.local.addExerciseExecution",
                    t
                )
                Wrapper.Error(t)
            }
        }

        override suspend fun deleteWorkout(
            workout: Workout
        ): Wrapper<Unit> {
            return try {
                Wrapper.Success(
                    local.deleteWorkout(
                        workout
                    )
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "WorkoutRepository.local.deleteWorkout",
                    t
                )
                Wrapper.Error(t)
            }
        }

        override suspend fun getWorkout(
            workout: Workout
        ): Wrapper<Flow<Workout>> {
            return try {
                Wrapper.Success(
                    local.getWorkout(workout)
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "WorkoutRepository.local.getWorkout",
                    t
                )
                Wrapper.Error(t)
            }
        }

        override suspend fun getWorkouts(): Wrapper<Flow<List<Workout>>> {
            return try {
                Wrapper.Success(
                    local.getWorkouts()
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "WorkoutRepository.local.getWorkouts",
                    t
                )
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
                TAG_DATA.error(
                    "WorkoutRepository.local.putWorkout",
                    t
                )
                Wrapper.Error(t)
            }
        }

        override suspend fun removeExerciseExecution(
            exerciseExecution: ExerciseExecution,
            workout: Workout
        ): Wrapper<Unit> {
            return try {
                Wrapper.Success(
                    local.removeExerciseExecution(
                        exerciseExecution,
                        workout
                    )
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "WorkoutRepository.local.removeExerciseExecution",
                    t
                )
                Wrapper.Error(t)
            }
        }

    }

    suspend fun addExerciseExecution(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    ): Wrapper<Unit>

    suspend fun deleteWorkout(
        workout: Workout
    ): Wrapper<Unit>

    suspend fun getWorkout(
        workout: Workout
    ): Wrapper<Flow<Workout>>

    suspend fun getWorkouts(): Wrapper<Flow<List<Workout>>>

    suspend fun putWorkout(
        workout: Workout
    ): Wrapper<Workout>

    suspend fun removeExerciseExecution(
        exerciseExecution: ExerciseExecution,
        workout: Workout
    ): Wrapper<Unit>

}
