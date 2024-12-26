package br.com.asoncsts.multi.gymtrack.data.user.local.model

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutEntity.WorkoutWithExerciseExecutions

// https://developer.android.com/training/data-storage/room/relationships/many-to-many
@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        workout: WorkoutEntity
    )

    @Transaction
    @Query("SELECT * FROM workout")
    suspend fun getWorkoutsWithExerciseExecutions(): List<WorkoutWithExerciseExecutions>

}
