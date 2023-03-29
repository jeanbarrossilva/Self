package com.jeanbarrossilva.self.wheel.android.domain.area

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AndroidAreaDao internal constructor() {
    @Query("SELECT * FROM areas")
    internal abstract fun selectAll(): Flow<List<AndroidAreaEntity>>

    @Insert
    internal abstract suspend fun insert(area: AndroidAreaEntity)

    @Insert
    internal abstract suspend fun insert(areas: List<AndroidAreaEntity>)

    @Query("DELETE FROM areas")
    internal abstract suspend fun deleteAll()
}
