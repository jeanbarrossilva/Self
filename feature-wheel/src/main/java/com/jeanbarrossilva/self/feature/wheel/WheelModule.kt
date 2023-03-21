package com.jeanbarrossilva.self.feature.wheel

import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRepository
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("FunctionName")
fun WheelModule(): Module {
    return module {
        single<WheelRepository> {
            InMemoryWheelRepository()
        }
    }
}
