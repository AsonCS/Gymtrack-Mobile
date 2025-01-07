package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExerciseExecutionEntity
import br.com.asoncsts.multi.gymtrack.data.user.local.model.ExerciseExecutionSimpleViewTuple
import br.com.asoncsts.multi.gymtrack.data.user.local.model.ExerciseExecutionWithExecutions
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseExecutionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        exerciseExecution: ExerciseExecutionEntity
    )

    @Query("SELECT exerciseExecutionId, name FROM exercise_execution")
    suspend fun getExerciseExecutions(): List<ExerciseExecutionSimpleViewTuple>

    @Query("SELECT * FROM exercise_execution WHERE exerciseExecutionId IN (:ids)")
    fun getExerciseExecutionsByIds(
        ids: List<String>
    ): Flow<List<ExerciseExecutionEntity>>

    // https://developer.android.com/training/data-storage/room/accessing-data#collection-parameters
    @Transaction
    @Query("SELECT * FROM exercise_execution WHERE exerciseExecutionId IN (:ids)")
    fun getExerciseExecutionsWithExecutions(
        ids: List<String>
    ): Flow<List<ExerciseExecutionWithExecutions>>

}
