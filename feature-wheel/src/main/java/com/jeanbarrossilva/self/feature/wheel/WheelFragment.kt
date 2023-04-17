package com.jeanbarrossilva.self.feature.wheel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.loadable.flow.unwrap
import com.jeanbarrossilva.self.feature.wheel.databinding.FragmentWheelBinding
import com.jeanbarrossilva.self.feature.wheel.scope.todo.ToDoComposerFragment
import com.jeanbarrossilva.self.platform.ui.core.binding.BindingFragment
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

internal class WheelFragment : BindingFragment<FragmentWheelBinding>() {
    private val repository by inject<WheelRepository>()
    private val editor by inject<WheelEditor>()
    private val viewModel by viewModels<WheelViewModel> {
        WheelViewModel.createFactory(repository, editor)
    }
    private val boundary by inject<WheelBoundary>()

    override val bindingClass = FragmentWheelBinding::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.doOnNonexistentWheel(::navigateToQuestionnaire)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.root?.setContent {
            SelfTheme {
                Wheel(viewModel, onToDoCompositionRequest = ::navigateToToDoComposer)
            }
        }
    }

    private fun navigateToQuestionnaire() {
        val navController = findNavController()
        boundary.navigateToQuestionnaire(navController)
    }

    private fun navigateToToDoComposer() {
        lifecycleScope.launch {
            val wheel = viewModel.getWheelLoadableFlow().unwrap().first()
            activity?.let { ToDoComposerFragment.show(it, wheel.areas) }
        }
    }
}
