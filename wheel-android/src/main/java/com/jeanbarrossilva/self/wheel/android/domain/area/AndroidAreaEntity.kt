package com.jeanbarrossilva.self.wheel.android.domain.area

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoEntity
import com.jeanbarrossilva.self.wheel.core.domain.area.Area

@Entity(tableName = "areas")
internal data class AndroidAreaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val parent: String,
    val name: String,
    val attention: Float
) {
    fun core(toDos: List<AndroidToDoEntity>): Area {
        assertRelationship(toDos)
        val coreToDos = toDos.map(AndroidToDoEntity::core)
        return Area(name, attention, coreToDos)
    }

    private fun assertRelationship(toDos: List<AndroidToDoEntity>) {
        val unrelated = toDos.filter { it.parent != name }
        val areAllRelated = unrelated.isEmpty()
        assert(areAllRelated) { "The following to-dos aren't related to $this: $unrelated." }
    }
}
