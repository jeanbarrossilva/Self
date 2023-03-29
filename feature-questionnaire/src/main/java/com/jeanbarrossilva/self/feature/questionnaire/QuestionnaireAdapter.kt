package com.jeanbarrossilva.self.feature.questionnaire

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.FamilyFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.LeisureFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.StudiesFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.answerable.type.WorkFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.type.AnnouncementFragment

internal class QuestionnaireAdapter(
    private val fragment: QuestionnaireFragment,
    private val onDone: () -> Unit
) : FragmentStateAdapter(fragment) {
    private val children
        get() = listOf(
            lazy { AnnouncementFragment(swiper, StepPosition.LEADING, onDone) },
            lazy { FamilyFragment(swiper, StepPosition.IN_BETWEEN, onDone) },
            lazy { WorkFragment(swiper, StepPosition.IN_BETWEEN, onDone) },
            lazy { StudiesFragment(swiper, StepPosition.IN_BETWEEN, onDone) },
            lazy { LeisureFragment(swiper, StepPosition.TRAILING, onDone) }
        )
    private val swiper
        get() = fragment.swiper
            ?: throw IllegalStateException("QuestionnaireFragment.swiper isn't initialized.")

    override fun getItemCount(): Int {
        return children.size
    }

    override fun createFragment(position: Int): Fragment {
        return children[position].value
    }
}
