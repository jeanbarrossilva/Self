package com.jeanbarrossilva.self.feature.wheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.self.feature.wheel.utils.feature
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class WheelViewModel(private val repository: WheelRepository) : ViewModel() {
    val wheel = flow { emitAll(repository.fetch()) }.map { it.first().feature() }.loadable()

    companion object {
        fun createFactory(repository: WheelRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(WheelViewModel::class) {
                    WheelViewModel(repository)
                }
            }
        }
    }
}
