package com.jeanbarrossilva.self.feature.questionnaire

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.self.feature.questionnaire.databinding.FragmentQuestionnaireBinding
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Swiper
import com.jeanbarrossilva.self.feature.questionnaire.utils.OnBackPressedCallback
import com.jeanbarrossilva.self.feature.questionnaire.utils.enforceSystemWindowInsets
import com.jeanbarrossilva.self.platform.ui.core.binding.BindingFragment
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class QuestionnaireFragment : BindingFragment<FragmentQuestionnaireBinding>() {
    private val register by inject<WheelRegister>()
    private val editor by inject<WheelEditor>()
    private val boundary by inject<QuestionnaireBoundary>()
    private val viewModel by viewModel<QuestionnaireViewModel> {
        parametersOf(requireActivity().application, register, editor)
    }

    internal var swiper: Swiper? = null

    override val bindingClass = FragmentQuestionnaireBinding::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.registerWheel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPager()
        swipeBackwardsOnBackPressed()
    }

    private fun swipeBackwardsOnBackPressed() {
        val callback = OnBackPressedCallback { swiper?.swipeBackwards() }
        activity?.onBackPressedDispatcher?.addCallback(callback)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.DESTROYED) {
                callback.remove()
            }
        }
    }

    private fun setUpPager() {
        binding?.root?.apply {
            swiper = Swiper(this)
            adapter = QuestionnaireAdapter(this@QuestionnaireFragment, ::onDone)
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

    private fun onDone() {
        viewModel.registerWheel()
        navigateToWheel()
    }

    private fun navigateToWheel() {
        val navController = findNavController()
        boundary.navigateToWheel(navController)
    }
}
