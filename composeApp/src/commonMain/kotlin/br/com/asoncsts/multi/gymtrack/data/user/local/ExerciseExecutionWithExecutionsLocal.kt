package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.dao.ExerciseExecutionWithExecutionsDao
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExerciseExecutionWithExecutionsCrossRefEntity
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution

interface ExerciseExecutionWithExecutionsLocal {

    class Impl(
        private val dao: ExerciseExecutionWithExecutionsDao
    ) : ExerciseExecutionWithExecutionsLocal {
        override suspend fun putExerciseExecutionWithExecutions(
            execution: Execution,
            exerciseExecution: ExerciseExecution
        ) {
            dao.insert(
                ExerciseExecutionWithExecutionsCrossRefEntity(
                    execution,
                    exerciseExecution
                )
            )
        }
    }

    suspend fun putExerciseExecutionWithExecutions(
        execution: Execution,
        exerciseExecution: ExerciseExecution
    )

}
