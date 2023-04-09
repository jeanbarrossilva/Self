package com.jeanbarrossilva.self.feature.wheel.scope.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class ToDoComposerViewModel(
    private val editor: WheelEditor,
    val areas: List<FeatureArea>
) : ViewModel() {
    private val nameMutableStateFlow = MutableStateFlow("")

    val nameFlow
        get() = nameMutableStateFlow.asStateFlow()

    fun setName(name: String) {
        nameMutableStateFlow.value = name
    }

    fun compose(area: FeatureArea) {
        viewModelScope.launch {
            editor.addToDo(WHEEL_NAME, area.name, nameFlow.value)
        }
    }

    companion object {
        private const val WHEEL_NAME = "Roda da vida"

        fun createFactory(editor: WheelEditor, areas: List<FeatureArea>):
            ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(ToDoComposerViewModel::class) {
                    ToDoComposerViewModel(editor, areas)
                }
            }
        }
    }
}