package com.jeanbarrossilva.self.app.module

import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelEditor
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRegister
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRepository
import com.jeanbarrossilva.self.wheel.android.utils.database
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("FunctionName")
internal fun CoreModule(): Module {
    return module {
        single<WheelRepository> {
            AndroidWheelRepository(
                database.wheelDao,
                database.areaDao,
                database.toDoDao,
                MainScope() + Dispatchers.IO
            )
        }
        single<WheelRegister> {
            AndroidWheelRegister(
                repository = get(),
                database.wheelDao,
                database.areaDao,
                database.toDoDao
            )
        }
        single<WheelEditor> {
            AndroidWheelEditor(
                repository = get(),
                database.wheelDao,
                database.areaDao,
                database.toDoDao
            )
        }
    }
}
