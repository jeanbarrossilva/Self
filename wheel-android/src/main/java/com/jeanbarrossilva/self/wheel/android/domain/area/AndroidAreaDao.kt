package com.jeanbarrossilva.self.wheel.android.domain.area

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AndroidAreaDao internal constructor() {
    @Query("SELECT * FROM areas")
    internal abstract fun selectAll(): Flow<List<AndroidAreaEntity>>

    @Query("SELECT * FROM areas WHERE name = :name")
    internal abstract fun selectByName(name: String): Flow<AndroidAreaEntity?>

    @Insert
    internal abstract suspend fun insert(area: AndroidAreaEntity)

    @Insert
    internal abstract suspend fun insert(areas: List<AndroidAreaEntity>)

    @Query("UPDATE areas SET name = :name WHERE id = :id")
    internal abstract suspend fun setName(id: Long, name: String)

    @Query("UPDATE areas SET attention = :attention WHERE id = :id")
    internal abstract suspend fun setAttention(id: Long, attention: Float)

    @Query("DELETE FROM areas")
    internal abstract suspend fun deleteAll()
}
