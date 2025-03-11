package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.dao.ExecutionDao
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExecutionEntity
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExecutionLocal {

    class Impl(
        private val dao: ExecutionDao
    ) : ExecutionLocal {

        override suspend fun putExecution(
            execution: Execution,
            exerciseExecution: ExerciseExecution
        ): Execution {
            val entity = ExecutionEntity(
                execution,
                exerciseExecution
            )
            dao.insert(entity)
            return execution.copy(
                id = entity.executionId
            )
        }

        override suspend fun deleteExecution(
            executionId: String
        ) {
            dao.delete(
                ExecutionEntity(
                    executionId
                )
            )
        }

    }

    suspend fun putExecution(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    ): Execution

    suspend fun deleteExecution(
        executionId: String
    )

}
