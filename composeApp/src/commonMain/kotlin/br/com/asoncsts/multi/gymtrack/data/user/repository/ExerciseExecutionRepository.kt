package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.ExerciseExecutionLocal
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import kotlinx.coroutines.flow.Flow

interface ExerciseExecutionRepository {

    class Impl(
        private val local: ExerciseExecutionLocal
    ) : ExerciseExecutionRepository {

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
            exerciseExecution: ExerciseExecution.Detail
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

    suspend fun getExerciseExecutions(): Wrapper<Flow<List<ExerciseExecution>>>

    suspend fun putExerciseExecution(
        exerciseExecution: ExerciseExecution.Detail
    ): Wrapper<ExerciseExecution.Detail>

}
