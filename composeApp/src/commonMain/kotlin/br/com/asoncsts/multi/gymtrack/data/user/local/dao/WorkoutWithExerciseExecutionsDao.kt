package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.WorkoutWithExerciseExecutionsCrossRefEntity
import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutWithExerciseExecutions
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutWithExerciseExecutionsDao {

    @Delete
    suspend fun delete(
        vararg crossRef: WorkoutWithExerciseExecutionsCrossRefEntity
    )

    @Transaction
    @Query("SELECT * FROM workout WHERE workoutId = :workoutId")
    fun getWorkoutWithExerciseExecutions(
        workoutId: String
    ): Flow<WorkoutWithExerciseExecutions>

    @Transaction
    @Query("SELECT * FROM workout")
    fun getWorkoutsWithExerciseExecutions(): Flow<List<WorkoutWithExerciseExecutions>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        crossRef: WorkoutWithExerciseExecutionsCrossRefEntity
    )

}
