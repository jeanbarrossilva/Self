package com.jeanbarrossilva.self.wheel.android.domain.todo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AndroidToDoDao internal constructor() {
    @Query("SELECT * FROM to_dos")
    internal abstract fun selectAll(): Flow<List<AndroidToDoEntity>>

    @Query("SELECT * FROM to_dos WHERE grandparent = :grandparent AND parent = :parent")
    internal abstract fun selectByRelationship(grandparent: String, parent: String):
        Flow<List<AndroidToDoEntity>>

    @Query("SELECT * FROM to_dos WHERE title = :title")
    internal abstract fun selectByTitle(title: String): Flow<AndroidToDoEntity?>

    @Insert
    internal abstract suspend fun insert(toDo: AndroidToDoEntity)

    @Insert
    internal abstract suspend fun insert(toDos: List<AndroidToDoEntity>)

    @Query("UPDATE to_dos SET title = :title WHERE id = :id")
    internal abstract suspend fun setTitle(id: Long, title: String)

    @Query("UPDATE to_dos SET is_done = :isDone WHERE id = :id")
    internal abstract suspend fun setDone(id: Long, isDone: Boolean)

    @Query("DELETE FROM to_dos")
    internal abstract suspend fun deleteAll()
}
