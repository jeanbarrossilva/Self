package com.jeanbarrossilva.self.feature.questionnaire.scope.step

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
    private var onNextListener: OnNextListener? = null
    private var onDoneListener: OnDoneListener? = null

    protected val position
        get() = requireArguments().getParcelableOrThrow<StepPosition>(POSITION_KEY)

    override val bindingClass = FragmentStepBinding::class

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener,
        onDoneListener: OnDoneListener
    ) : this() {
        this.onPreviousListener = onPreviousListener
        this.onNextListener = onNextListener
        this.onDoneListener = onDoneListener
        arguments = bundleOf(POSITION_KEY to position)
    }

    fun interface OnPreviousListener {
        fun onPrevious()
    }

    fun interface OnNextListener {
        fun onNext()
    }

    fun interface OnDoneListener {
        fun onDone()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setContent()
    }

    override fun onResume() {
        super.onResume()
        onFocus()
    }

    override fun onDestroy() {
        super.onDestroy()
        onNextListener = null
        onDoneListener = null
    }

    @Composable
    protected open fun Content() {
        Step(position, canProceed = true, title = { Title() }, ::onPrevious, ::onNext)
    }

    protected open fun onFocus() {
        imeController?.close()
    }

    @CallSuper
    protected open fun onPrevious() {
        onPreviousListener?.onPrevious()
    }

    @CallSuper
    protected open fun onNext() {
        if (position == StepPosition.TRAILING) {
            onDoneListener?.onDone()
        } else {
            onNextListener?.onNext()
        }
    }

    @Composable
    protected abstract fun Title()

    private fun setOnDoneListener(listener: OnDoneListener) {
        onDoneListener = listener
    }

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
