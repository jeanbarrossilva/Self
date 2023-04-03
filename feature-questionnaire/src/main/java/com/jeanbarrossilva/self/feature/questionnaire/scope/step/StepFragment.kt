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
    private var swiper: Swiper? = null
    private var onDoneListener: OnDoneListener? = null

    protected val position
        get() = requireArguments().getParcelableOrThrow<StepPosition>(POSITION_KEY)

    override val bindingClass = FragmentStepBinding::class

    constructor(
        swiper: Swiper,
        position: StepPosition,
        onDoneListener: OnDoneListener
    ) : this() {
        this.swiper = swiper
        this.onDoneListener = onDoneListener
        arguments = bundleOf(POSITION_KEY to position)
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
        swiper = null
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
        swiper?.swipeBackwards()
    }

    @CallSuper
    protected open fun onNext() {
        if (position == StepPosition.TRAILING) {
            onDoneListener?.onDone()
        } else {
            swiper?.swipeForward()
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
