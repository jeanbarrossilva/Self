package com.jeanbarrossilva.self.feature.wheel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jeanbarrossilva.self.feature.wheel.databinding.FragmentWheelBinding
import com.jeanbarrossilva.self.feature.wheel.scope.editingsheet.EditingSheetFragment
import com.jeanbarrossilva.self.feature.wheel.ui.theme.SelfTheme
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import org.koin.android.ext.android.inject

internal class WheelFragment : Fragment() {
    private val repository by inject<WheelRepository>()
    private val editor by inject<WheelEditor>()
    private val viewModel by viewModels<WheelViewModel> {
        WheelViewModel.createFactory(repository, editor)
    }
    private var binding: FragmentWheelBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.root?.setContent {
            SelfTheme {
                Wheel(viewModel, onEdit = ::edit)
            }
        }
    }

    private fun edit() {
        activity?.let(EditingSheetFragment::show)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
