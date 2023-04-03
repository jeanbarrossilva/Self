package com.jeanbarrossilva.self.feature.questionnaire

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.AnswerableStepFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.FamilyFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.LeisureFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.StudiesFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.WorkFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.type.AnnouncementFragment

internal class QuestionnaireAdapter(
    fragment: QuestionnaireFragment,
    private val onPreviousListener: StepFragment.OnPreviousListener,
    private val onNextListener: StepFragment.OnNextListener,
    private val onNextAnswerableListener: AnswerableStepFragment.OnNextListener,
    private val onDoneListener: StepFragment.OnDoneListener
) : FragmentStateAdapter(fragment) {
    private val children
        get() = listOf(
            lazy {
                AnnouncementFragment(
                    StepPosition.LEADING,
                    onPreviousListener,
                    onNextListener,
                    onDoneListener
                )
            },
            lazy {
                FamilyFragment(
                    StepPosition.IN_BETWEEN,
                    onPreviousListener,
                    onNextAnswerableListener,
                    onDoneListener
                )
            },
            lazy {
                WorkFragment(
                    StepPosition.IN_BETWEEN,
                    onPreviousListener,
                    onNextAnswerableListener,
                    onDoneListener
                )
            },
            lazy {
                StudiesFragment(
                    StepPosition.IN_BETWEEN,
                    onPreviousListener,
                    onNextAnswerableListener,
                    onDoneListener
                )
            },
            lazy {
                LeisureFragment(
                    StepPosition.TRAILING,
                    onPreviousListener,
                    onNextAnswerableListener,
                    onDoneListener
                )
            }
        )

    override fun getItemCount(): Int {
        return children.size
    }

    override fun createFragment(position: Int): Fragment {
        return children[position].value
    }
}
