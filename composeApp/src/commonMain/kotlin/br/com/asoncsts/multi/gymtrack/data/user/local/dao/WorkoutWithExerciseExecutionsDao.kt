package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.WorkoutWithExerciseExecutionsCrossRefEntity

@Dao
interface WorkoutWithExerciseExecutionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        workoutWithExerciseExecutionsCrossRef: WorkoutWithExerciseExecutionsCrossRefEntity
    )

}
