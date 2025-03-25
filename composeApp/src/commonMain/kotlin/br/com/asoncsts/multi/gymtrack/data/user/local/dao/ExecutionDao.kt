package br.com.asoncsts.multi.gymtrack.data.user.local.dao

import androidx.room.*
import br.com.asoncsts.multi.gymtrack.data.user.local.entities.ExecutionEntity

@Dao
interface ExecutionDao {

    @Delete
    suspend fun delete(
        vararg execution: ExecutionEntity
    )

    @Query("SELECT * FROM execution")
    suspend fun getExecutions(): List<ExecutionEntity>

    @Query("SELECT * FROM execution WHERE executionId = :id OR executionIdParent = :id")
    suspend fun getExecutionsById(
        id: String
    ): List<ExecutionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        vararg execution: ExecutionEntity
    )

}
