package br.com.asoncsts.multi.gymtrack.data.user.local

import br.com.asoncsts.multi.gymtrack.data.user.local.dao.ExerciseExecutionDao
import br.com.asoncsts.multi.gymtrack.data.user.local.dao.ExerciseExecutionWithExecutionsDao
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExerciseExecutionEntity
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ExerciseExecutionLocal {

    class Impl(
        private val dao: ExerciseExecutionDao,
        private val withExecutionsDao: ExerciseExecutionWithExecutionsDao
    ) : ExerciseExecutionLocal {

        override suspend fun deleteExerciseExecution(
            exerciseExecution: ExerciseExecution
        ) {
            dao.delete(
                ExerciseExecutionEntity(
                    exerciseExecution
                )
            )
        }

        override suspend fun getExerciseExecutions(): Flow<List<ExerciseExecution>> {
            return dao.getExerciseExecutions()
                .map { list ->
                    list.map { it.toExerciseExecution() }
                }
        }

        override suspend fun getExerciseExecutionsWithExecutions(
            vararg ids: String
        ): Flow<List<ExerciseExecution.Detail>> {
            return withExecutionsDao.getExerciseExecutionWithExecutions(
                ids.toList()
            ).map { list ->
                list.map { it.toExerciseExecution() }
            }
        }

        override suspend fun putExerciseExecution(
            exerciseExecution: ExerciseExecution.Detail
        ): ExerciseExecution.Detail {
            val entity = ExerciseExecutionEntity(
                exerciseExecution
            )
            dao.insert(entity)
            return exerciseExecution.copy(
                id = entity.exerciseExecutionId
            )
        }
    }

    suspend fun deleteExerciseExecution(
        exerciseExecution: ExerciseExecution
    )

    suspend fun getExerciseExecutions(): Flow<List<ExerciseExecution>>

    suspend fun getExerciseExecutionsWithExecutions(
        vararg ids: String
    ): Flow<List<ExerciseExecution.Detail>>

    suspend fun putExerciseExecution(
        exerciseExecution: ExerciseExecution.Detail
    ): ExerciseExecution.Detail

}
