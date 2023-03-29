package com.jeanbarrossilva.self.feature.questionnaire

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.jeanbarrossilva.self.feature.questionnaire.databinding.FragmentQuestionnaireBinding
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Swiper
import com.jeanbarrossilva.self.platform.ui.core.binding.BindingFragment
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
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
    private val onBackPressedCallback = object : OnBackPressedCallback(enabled = true) {
        override fun handleOnBackPressed() {
            swiper?.swipeBackwards()
        }
    }

    internal var swiper: Swiper? = null

    override val bindingClass = FragmentQuestionnaireBinding::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)
        viewModel.registerWheel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.root?.apply {
            swiper = Swiper(this)
            adapter = QuestionnaireAdapter(this@QuestionnaireFragment, ::onDone)
            isUserInputEnabled = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onBackPressedCallback.remove()
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
