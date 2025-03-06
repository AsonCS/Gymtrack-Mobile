package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.ExerciseExecutionWithExecutionsLocal
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExerciseExecutionWithExecutionsRepository {

    class Impl(
        private val local: ExerciseExecutionWithExecutionsLocal
    ) : ExerciseExecutionWithExecutionsRepository {
        override suspend fun putExerciseExecutionWithExecutions(
            execution: Execution,
            exerciseExecution: ExerciseExecution
        ): Wrapper<Unit> {
            return try {
                Wrapper.Success(
                    local.putExerciseExecutionWithExecutions(
                        execution,
                        exerciseExecution
                    )
                )
            } catch (t: Throwable) {
                TAG_DATA.error(
                    "ExerciseExecutionWithExecutionsRepository.local.putExerciseExecutionWithExecutions",
                    t
                )
                Wrapper.Error(t)
            }
        }
    }

    suspend fun putExerciseExecutionWithExecutions(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    ): Wrapper<Unit>

}
