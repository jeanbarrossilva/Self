package com.jeanbarrossilva.self.feature.wheel.scope.todo

import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.platform.ui.core.sheet.SheetFragment
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import org.koin.android.ext.android.inject

internal class ToDoComposerFragment() : SheetFragment() {
    private val editor by inject<WheelEditor>()
    private var areas = emptyList<FeatureArea>()
    private val viewModel by viewModels<ToDoComposerViewModel> {
        ToDoComposerViewModel.createFactory(editor, areas)
    }

    constructor(areas: List<FeatureArea>) : this() {
        this.areas = areas
    }

    @Composable
    override fun Content() {
        ToDoComposer(viewModel, onComposition = ::dismiss)
    }

    companion object {
        fun show(activity: FragmentActivity, areas: List<FeatureArea>) {
            ToDoComposerFragment(areas).show(activity.supportFragmentManager, "to_do_composer")
        }
    }
}
