package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.dao.ExerciseExecutionDao
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExerciseExecutionEntity
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ExerciseExecutionLocal {

    class Impl(
        private val dao: ExerciseExecutionDao
    ) : ExerciseExecutionLocal {

        override suspend fun getExerciseExecutions(): List<ExerciseExecution.SimpleView> {
            return dao.getExerciseExecutions()
                .map { it.toExerciseExecution() }
        }

        override suspend fun getExerciseExecutions(
            getExercise: (alias: String) -> Exercise,
            ids: List<String>
        ): Flow<List<ExerciseExecution>> {
            return dao.getExerciseExecutionsByIds(ids)
                .map { list ->
                    list.map { it.toExerciseExecution(getExercise) }
                }
        }

        override suspend fun getExerciseExecutionsWithExecutions(
            getExercise: (alias: String) -> Exercise,
            ids: List<String>
        ): Flow<List<ExerciseExecution.Detail>> {
            return dao.getExerciseExecutionsWithExecutions(ids)
                .map { list ->
                    list.map { it.toExerciseExecution(getExercise) }
                }
        }

        override suspend fun putExerciseExecution(
            exerciseExecution: ExerciseExecution.Detail
        ): String {
            val entity = ExerciseExecutionEntity(
                exerciseExecution
            )
            dao.insert(entity)
            return entity.exerciseExecutionId
        }
    }

    suspend fun getExerciseExecutions(): List<ExerciseExecution.SimpleView>

    suspend fun getExerciseExecutions(
        getExercise: (alias: String) -> Exercise,
        ids: List<String>
    ): Flow<List<ExerciseExecution>>

    suspend fun getExerciseExecutionsWithExecutions(
        getExercise: (alias: String) -> Exercise,
        ids: List<String>
    ): Flow<List<ExerciseExecution.Detail>>

    suspend fun putExerciseExecution(
        exerciseExecution: ExerciseExecution.Detail
    ): String

}
