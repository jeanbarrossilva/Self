package com.jeanbarrossilva.self.app.module

import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelEditor
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRegister
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRepository
import com.jeanbarrossilva.self.wheel.android.utils.database
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

@Suppress("FunctionName")
internal fun CoreModule(): Module {
    return CoreModule(
        { AndroidWheelRepository(database.wheelDao, database.areaDao, database.toDoDao) },
        {
            AndroidWheelRegister(
                repository = get(),
                database.wheelDao,
                database.areaDao,
                database.toDoDao
            )
        },
        {
            AndroidWheelEditor(
                repository = get(),
                database.wheelDao,
                database.areaDao,
                database.toDoDao
            )
        }
    )
}

@Suppress("FunctionName")
internal fun CoreModule(
    repository: Scope.() -> WheelRepository,
    register: Scope.() -> WheelRegister,
    editor: Scope.() -> WheelEditor
): Module {
    return module {
        single { repository() }
        single { register() }
        single { editor() }
    }
}
