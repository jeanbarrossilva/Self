package com.jeanbarrossilva.self.wheel.android.domain.wheel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaEntity
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoEntity
import com.jeanbarrossilva.self.wheel.core.domain.Wheel

@Entity(tableName = "wheels")
internal data class AndroidWheelEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String
) {
    fun core(areas: List<AndroidAreaEntity>, toDos: List<AndroidToDoEntity>): Wheel {
        assertRelationship(areas)
        val areaToDoEntitiesMap = areas.associateWith { areaEntity ->
            toDos.filter { toDoEntity ->
                toDoEntity.parent == areaEntity.name
            }
        }
        val coreAreas = areas.map {
            it.core(
                areaToDoEntitiesMap[it] ?: throw IllegalStateException(
                    "$it's to-dos could not be found."
                )
            )
        }
        return Wheel(name, coreAreas)
    }

    private fun assertRelationship(areas: List<AndroidAreaEntity>) {
        val unrelated = areas.filter { it.parent != name }
        val areAllRelated = unrelated.isEmpty()
        assert(areAllRelated) { "The following areas aren't related to $this: $unrelated" }
    }
}
