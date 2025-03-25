package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExerciseExecutionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseExecutionDao {

    @Delete
    suspend fun delete(
        vararg exerciseExecution: ExerciseExecutionEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        exerciseExecution: ExerciseExecutionEntity
    )

    @Transaction
    @Query("SELECT * FROM exercise_execution")
    fun getExerciseExecutions(): Flow<List<ExerciseExecutionEntity>>

}
