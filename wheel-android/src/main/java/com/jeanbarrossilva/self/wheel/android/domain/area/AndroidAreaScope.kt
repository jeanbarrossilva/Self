package com.jeanbarrossilva.self.wheel.android.domain.area

import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoScope
import com.jeanbarrossilva.self.wheel.android.utils.domain
import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.area.AreaScope
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDoScope
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.core.utils.get
import kotlinx.coroutines.flow.first

internal class AndroidAreaScope(
    wheelScope: WheelScope,
    private val areaDao: AndroidAreaDao,
    private val toDoDao: AndroidToDoDao,
    private val wheel: Wheel,
    private val area: Area
) : AreaScope(wheelScope, area) {
    private val preExistentToDos
        get() = toDos.filter { area.toDos[it.title] != null }

    override fun createToDoScope(toDo: ToDo): ToDoScope {
        return AndroidToDoScope(this, toDoDao, toDo)
    }

    override suspend fun onSubmission() {
        val entity = areaDao.selectByName(area.name).first() ?: throw IllegalStateException(
            "No area named \"${area.name} found."
        )
        val id = entity.id
        areaDao.setName(id, name)
        areaDao.setAttention(id, attention)
        updateAndAddToDos()
    }

    suspend fun updateAndAddToDos() {
        updatePreExistentToDos()
        addNewToDos()
    }

    private suspend fun updatePreExistentToDos() {
        preExistentToDos.forEach {
            val title = it.title
            onToDo(title).setTitle(title).setDone(it.isDone).submit()
        }
    }

    private suspend fun addNewToDos() {
        toDos
            .filter { preExistentToDos[it.title] == null }
            .map { it.domain(grandparent = wheel.name, parent = name) }
            .onEach { toDoDao.insert(it) }
            .forEach {
                val title = it.title
                addToDo(title).onToDo(title).setDone(it.isDone).submit()
            }
    }
}
