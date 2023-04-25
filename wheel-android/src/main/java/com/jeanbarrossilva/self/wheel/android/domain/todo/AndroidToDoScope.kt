package com.jeanbarrossilva.self.wheel.android.domain.todo

import com.jeanbarrossilva.self.wheel.core.domain.area.AreaScope
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDoScope
import kotlinx.coroutines.flow.first

internal class AndroidToDoScope(
    areaScope: AreaScope,
    private val dao: AndroidToDoDao,
    private val toDo: ToDo
) : ToDoScope(areaScope, toDo) {
    override suspend fun onSubmission() {
        val id = dao.selectByTitle(toDo.title).first()?.id ?: throw IllegalStateException(
            "No to-do identified titled ${toDo.title} found."
        )
        dao.setTitle(id, title)
        dao.setDone(id, isDone)
    }
}
