package br.com.asoncsts.multi.gymtrack.data.user.local.model

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.model.WorkoutEntity.WorkoutWithExerciseExecutions

@Dao
interface WorkoutDao {

    @Transaction
    @Query("SELECT * FROM workout")
    suspend fun getWorkoutsWithExerciseExecutions(): List<WorkoutWithExerciseExecutions>

}
