package com.jeanbarrossilva.self.feature.questionnaire.step

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import com.jeanbarrossilva.self.feature.questionnaire.databinding.FragmentStepBinding
import com.jeanbarrossilva.self.feature.questionnaire.utils.getParcelableOrThrow
import com.jeanbarrossilva.self.platform.ui.core.binding.BindingFragment
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.imeController

internal abstract class StepFragment() : BindingFragment<FragmentStepBinding>() {
    private var onPreviousListener: OnPreviousListener? = null

    protected val position
        get() = requireArguments().getParcelableOrThrow<StepPosition>(POSITION_KEY)

    override val bindingClass = FragmentStepBinding::class

    constructor(position: StepPosition, onPreviousListener: OnPreviousListener) : this() {
        this.onPreviousListener = onPreviousListener
        arguments = bundleOf(POSITION_KEY to position)
    }

    fun interface OnPreviousListener {
        fun onPrevious()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setContent()
    }

    override fun onResume() {
        super.onResume()
        onFocus()
    }

    @Composable
    protected abstract fun Content()

    protected open fun onFocus() {
        imeController?.close()
    }

    @CallSuper
    protected open fun onPrevious() {
        onPreviousListener?.onPrevious()
    }

    @Composable
    protected abstract fun Title()

    private fun setContent() {
        binding?.composeView?.setContent {
            SelfTheme {
                Content()
            }
        }
    }

    companion object {
        private const val POSITION_KEY = "position"
    }
}
