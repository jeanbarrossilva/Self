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
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.unanswerable.UnanswerableStepFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.unanswerable.type.AnnouncementFragment

internal class QuestionnaireAdapter(
    fragment: QuestionnaireFragment,
    onPreviousListener: StepFragment.OnPreviousListener,
    onNextUnanswerableListener: UnanswerableStepFragment.OnNextListener,
    onNextAnswerableListener: AnswerableStepFragment.OnNextListener
) : FragmentStateAdapter(fragment) {
    private val children = listOf(
        AnnouncementFragment(StepPosition.LEADING, onPreviousListener, onNextUnanswerableListener),
        FamilyFragment(StepPosition.IN_BETWEEN, onPreviousListener, onNextAnswerableListener),
        WorkFragment(StepPosition.IN_BETWEEN, onPreviousListener, onNextAnswerableListener),
        StudiesFragment(StepPosition.IN_BETWEEN, onPreviousListener, onNextAnswerableListener),
        LeisureFragment(StepPosition.TRAILING, onPreviousListener, onNextAnswerableListener)
    )

    override fun getItemCount(): Int {
        return children.size
    }

    override fun createFragment(position: Int): Fragment {
        return children[position]
    }
}
