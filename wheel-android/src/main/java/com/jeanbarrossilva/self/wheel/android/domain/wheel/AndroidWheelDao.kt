package com.jeanbarrossilva.self.wheel.android.domain.wheel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AndroidWheelDao internal constructor() {
    @Query("SELECT * FROM areas")
    internal abstract fun selectAll(): Flow<List<AndroidWheelEntity>>

    @Query("SELECT * FROM areas WHERE name = :name")
    internal abstract fun selectByName(name: String): Flow<List<AndroidWheelEntity>>

    @Insert
    internal abstract suspend fun insert(wheel: AndroidWheelEntity)

    @Query("UPDATE wheels SET name = :name WHERE id = :id")
    internal abstract suspend fun setName(id: Long, name: String)

    @Query("DELETE FROM areas")
    internal abstract suspend fun deleteAll()
}
