package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.WorkoutEntity

// https://developer.android.com/training/data-storage/room/relationships/many-to-many
@Dao
interface WorkoutDao {

    @Delete
    suspend fun delete(
        vararg workout: WorkoutEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        workout: WorkoutEntity
    )

}
