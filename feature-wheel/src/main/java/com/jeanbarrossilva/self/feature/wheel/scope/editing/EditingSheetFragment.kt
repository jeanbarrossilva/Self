package com.jeanbarrossilva.self.feature.wheel.scope.editing

import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ToDoComposerFragment
import com.jeanbarrossilva.self.platform.ui.core.sheet.SheetFragment

internal class EditingSheetFragment() : SheetFragment() {
    private var areas = emptyList<FeatureArea>()

    constructor(areas: List<FeatureArea>) : this() {
        this.areas = areas
    }

    @Composable
    override fun Content() {
        EditingSheet(
            onToDoCompositionRequest = ::navigateToToDoComposer,
            onEditRequest = ::navigateToWheelEditor
        )
    }

    private fun navigateToToDoComposer() {
        activity?.let {
            ToDoComposerFragment.show(it, areas)
        }
    }

    private fun navigateToWheelEditor() {
    }

    companion object {
        fun show(activity: FragmentActivity, areas: List<FeatureArea>) {
            EditingSheetFragment(areas).show(activity.supportFragmentManager, "editing_sheet")
        }
    }
}
