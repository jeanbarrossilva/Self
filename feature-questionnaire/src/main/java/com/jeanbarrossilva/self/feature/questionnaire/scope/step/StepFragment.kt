package com.jeanbarrossilva.self.feature.questionnaire.scope.step

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.feature.questionnaire.databinding.FragmentStepBinding
import com.jeanbarrossilva.self.feature.questionnaire.utils.view.applySystemWindowInsetsAsMargin
import com.jeanbarrossilva.self.platform.ui.core.binding.BindingFragment
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.imeController

internal abstract class StepFragment : BindingFragment<FragmentStepBinding>() {
    protected abstract val swiper: Swiper
    protected abstract val position: StepPosition

    override val bindingClass = FragmentStepBinding::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setContent()
    }

    override fun onResume() {
        super.onResume()
        applySystemWindowInsetsAsMargins()
        onFocus()
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
        swiper.swipeBackwards()
    }

    @CallSuper
    protected open fun onNext() {
        if (position == StepPosition.TRAILING) {
            onDone()
        } else {
            swiper.swipeForward()
        }
    }

    @Composable
    protected abstract fun Title()

    protected abstract fun onDone()

    private fun setContent() {
        binding?.composeView?.setContent {
            SelfTheme {
                Content()
            }
        }
    }

    private fun applySystemWindowInsetsAsMargins() {
        view?.applySystemWindowInsetsAsMargin { width, height ->
            FrameLayout.LayoutParams(width, height)
        }
    }
}
