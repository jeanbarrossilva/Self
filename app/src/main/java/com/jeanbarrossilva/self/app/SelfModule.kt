package com.jeanbarrossilva.self.app

import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelEditor
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRegister
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRepository
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("FunctionName")
internal fun SelfModule(): Module {
    return module {
        single<WheelRepository> { InMemoryWheelRepository() }
        single<WheelRegister> {
            InMemoryWheelRegister(get<WheelRepository>() as InMemoryWheelRepository)
        }
        single<WheelEditor> {
            InMemoryWheelEditor(get<WheelRepository>() as InMemoryWheelRepository)
        }
    }
}
