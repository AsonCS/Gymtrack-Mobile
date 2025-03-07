package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.model.ExerciseExecutionWithExecutions
import kotlinx.coroutines.flow.Flow

// https://developer.android.com/training/data-storage/room/relationships/one-to-many
@Dao
interface ExerciseExecutionWithExecutionsDao {

    // https://developer.android.com/training/data-storage/room/accessing-data#collection-parameters
    @Transaction
    @Query("SELECT * FROM exercise_execution WHERE exerciseExecutionId IN (:ids)")
    fun getExerciseExecutionWithExecutions(
        ids: List<String>
    ): Flow<List<ExerciseExecutionWithExecutions>>

}
