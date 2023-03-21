package com.jeanbarrossilva.self.feature.wheel.scope.editingsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeanbarrossilva.self.feature.wheel.R
import com.jeanbarrossilva.self.feature.wheel.databinding.FragmentEditingSheetBinding
import com.jeanbarrossilva.self.feature.wheel.ui.theme.SelfTheme

internal class EditingSheetFragment : BottomSheetDialogFragment() {
    private var binding: FragmentEditingSheetBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_Self_Components_Dialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                    ?.background = null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditingSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.root?.setContent {
            SelfTheme {
                EditingSheet(onEditAreas = { }, onAddToDo = { })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun show(activity: FragmentActivity) {
            EditingSheetFragment().show(activity.supportFragmentManager, "editing_fragment")
        }
    }
}
