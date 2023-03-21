package com.jeanbarrossilva.self.feature.wheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository

internal class WheelViewModel(private val repository: WheelRepository) : ViewModel() {
    val wheel = loadable {
        load(FeatureWheel.sample)
    }

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
