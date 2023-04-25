package com.jeanbarrossilva.self.feature.wheel.scope.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

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
        // TODO: Figure out a way to not have to block the current thread. Simply replacing this
        //  call by viewModelScope.launch hangs and doesn't complete the the job.
        runBlocking {
            editor.onWheel(WHEEL_NAME).onArea(area.name).addToDo(nameFlow.value).submit()
        }
    }

    companion object {
        const val WHEEL_NAME = "Roda da vida"

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
