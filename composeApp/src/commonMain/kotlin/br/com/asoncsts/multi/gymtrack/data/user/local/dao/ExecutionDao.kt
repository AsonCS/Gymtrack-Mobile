package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExecutionEntity

@Dao
interface ExecutionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        execution: ExecutionEntity
    )

}
