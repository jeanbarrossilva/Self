package com.jeanbarrossilva.self.feature.questionnaire

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.self.feature.questionnaire.databinding.FragmentQuestionnaireBinding
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import com.jeanbarrossilva.self.feature.questionnaire.utils.OnBackPressedCallback
import com.jeanbarrossilva.self.feature.questionnaire.utils.enforceSystemWindowInsets
import com.jeanbarrossilva.self.platform.ui.core.binding.BindingFragment
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

internal class QuestionnaireFragment : BindingFragment<FragmentQuestionnaireBinding>() {
    private val register by inject<WheelRegister>()
    private val editor by inject<WheelEditor>()
    private val boundary by inject<QuestionnaireBoundary>()
    private val viewModel by viewModels<QuestionnaireViewModel> {
        QuestionnaireViewModel.createFactory(requireActivity().application, register, editor)
    }

    override val bindingClass = FragmentQuestionnaireBinding::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPager()
        swipeBackOnBackPressed()
    }

    private fun setUpPager() {
        binding?.root?.apply {
            adapter = QuestionnaireAdapter(
                this@QuestionnaireFragment,
                ::onPrevious,
                onNextUnanswerableListener = ::navigateToNextPageOrFinish,
                onNextAnswerableListener = ::answer
            )
            isUserInputEnabled = false

            // ViewPager2 appears to ignore the system's window insets and lays its content behind
            // status and navigation bars. Meticulous observation and constant fixes are required
            // for it not to happen and for these to be actually considered.
            //
            // This issue has been reported and the given fix hasn't done the job in my case, so
            // enforceSystemWindowInsets handles it all gracefully.
            // (https://issuetracker.google.com/issues/145617093#comment10)
            enforceSystemWindowInsets<FrameLayout.LayoutParams>()
        }
    }

    private fun swipeBackOnBackPressed() {
        val callback = OnBackPressedCallback(handleOnBackPressed = ::onPrevious)
        activity?.onBackPressedDispatcher?.addCallback(callback)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.DESTROYED) {
                callback.remove()
            }
        }
    }

    private fun onPrevious() {
        binding?.root?.run {
            setCurrentItem(currentItem - 1, true)
        }
    }

    private fun answer(areaName: String, @Attention answer: Float) {
        viewModel.answer(areaName, answer)
        navigateToNextPageOrFinish()
    }

    private fun navigateToNextPageOrFinish() {
        binding?.root?.run {
            if (currentItem == adapter?.itemCount?.minus(1)) {
                onDone()
            } else {
                setCurrentItem(currentItem + 1, true)
            }
        }
    }

    private fun onDone() {
        viewModel.registerWheel()
        navigateToWheel()
    }

    private fun navigateToWheel() {
        val navController = findNavController()
        boundary.navigateToWheel(navController)
    }
}
