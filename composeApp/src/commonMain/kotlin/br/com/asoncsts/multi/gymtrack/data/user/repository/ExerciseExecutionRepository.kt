package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.ExerciseExecutionLocal
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ExerciseExecutionRepository {

    class Impl(
        private val local: ExerciseExecutionLocal
    ) : ExerciseExecutionRepository {

        override suspend fun getExerciseExecution(
            id: String
        ): Wrapper<Flow<ExerciseExecution.Detail>> {
            return try {
                Wrapper.Success(
                    local.getExerciseExecutionsWithExecutions(id)
                        .map { it.first() }
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "UserExerciseRepository.local.getExerciseExecutionsWithExecutions",
                    t
                )
                Wrapper.Error(t)
            }
        }

        override suspend fun getExerciseExecutions(): Wrapper<Flow<List<ExerciseExecution>>> {
            return try {
                Wrapper.Success(
                    local.getExerciseExecutions()
                )
            } catch (t: Throwable) {
                TAG_DATA.error("UserExerciseRepository.local.getExerciseExecutions", t)
                Wrapper.Error(t)
            }
        }

        override suspend fun putExerciseExecution(
            exerciseExecution: ExerciseExecution.Detail,
            workout: Workout?
        ): Wrapper<ExerciseExecution.Detail> {
            return try {
                Wrapper.Success(
                    local.putExerciseExecution(exerciseExecution)
                )
            } catch (t: Throwable) {
                TAG_DATA.error("UserExerciseRepository.local.putWorkout", t)
                Wrapper.Error(t)
            }
        }

    }

    suspend fun getExerciseExecution(
        id: String
    ): Wrapper<Flow<ExerciseExecution.Detail>>

    suspend fun getExerciseExecutions(): Wrapper<Flow<List<ExerciseExecution>>>

    suspend fun putExerciseExecution(
        exerciseExecution: ExerciseExecution.Detail,
        workout: Workout?
    ): Wrapper<ExerciseExecution.Detail>

}
