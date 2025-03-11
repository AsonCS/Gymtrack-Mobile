package br.com.asoncsts.multi.gymtrack.data.user.repository

import br.com.asoncsts.multi.gymtrack.data._utils.TAG_DATA
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.user.local.ExecutionLocal
import br.com.asoncsts.multi.gymtrack.extension.error
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExecutionRepository {

    class Impl(
        private val local: ExecutionLocal
    ) : ExecutionRepository {

        override suspend fun putExecution(
            execution: Execution,
            exerciseExecution: ExerciseExecution
        ): Wrapper<Execution> {
            return try {
                Wrapper.Success(
                    local.putExecution(
                        execution,
                        exerciseExecution
                    )
                )
            } catch (t: Throwable) {
                TAG_DATA.error("ExecutionRepository.local.putExecution", t)
                Wrapper.Error(t)
            }
        }

    }

    suspend fun putExecution(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    ): Wrapper<Execution>

}
