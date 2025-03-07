package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExecutionEntity

interface ExecutionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        execution: ExecutionEntity
    )

}
