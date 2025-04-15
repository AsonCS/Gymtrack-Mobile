package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.dao.ExecutionDao
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExecutionEntity
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExecutionLocal {

    class Impl(
        private val dao: ExecutionDao
    ) : ExecutionLocal {

        override suspend fun deleteExecution(
            executionId: String,
            exerciseExecution: ExerciseExecution
        ) {
            dao.delete(
                *dao.getExecutionsById(
                    executionId
                ).toTypedArray()
            )
            insertOrderedEntities(exerciseExecution.id)
        }

        override suspend fun putExecution(
            execution: Execution,
            exerciseExecution: ExerciseExecution
        ): Execution {
            val entity = ExecutionEntity(
                execution,
                exerciseExecution
            )

            insertOrderedEntities(exerciseExecution.id) {
                toMutableList().apply {
                    remove(entity)
                    if (entity.executionIdParent != null) {
                        add(
                            entity
                        )
                    } else {
                        add(
                            entity.order,
                            entity
                        )
                    }
                }
            }

            return execution.copy(
                id = entity.executionId
            )
        }

        private suspend fun insertOrderedEntities(
            exerciseExecutionId: String,
            applyBlock: List<ExecutionEntity>.() -> List<ExecutionEntity> = { this }
        ) {
            dao.insert(
                *dao.getExecutionsByExerciseExecutionId(exerciseExecutionId)
                    .sortedBy { it.order }
                    .applyBlock()
                    .mapIndexed { index, executionEntity ->
                        executionEntity.copy(
                            order = index
                        )
                    }.toTypedArray()
            )
        }

    }

    suspend fun deleteExecution(
        executionId: String,
        exerciseExecution: ExerciseExecution
    )

    suspend fun putExecution(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    ): Execution

}
