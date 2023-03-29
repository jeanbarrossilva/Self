package com.jeanbarrossilva.self.wheel.android.domain.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.self.wheel.core.domain.ToDo

@Entity(tableName = "to_dos")
internal data class AndroidToDoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val grandparent: String,
    val parent: String,
    val title: String,
    @ColumnInfo(name = "is_done") val isDone: Boolean
) {
    fun core(): ToDo {
        return ToDo(title, isDone)
    }
}
