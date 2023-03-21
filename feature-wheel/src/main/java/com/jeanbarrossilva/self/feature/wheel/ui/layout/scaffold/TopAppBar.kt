package com.jeanbarrossilva.self.feature.wheel.ui.layout.scaffold

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.ui.theme.SelfTheme
import com.jeanbarrossilva.self.feature.wheel.utils.elevated
import com.jeanbarrossilva.self.feature.wheel.utils.fontSize
import me.onebone.toolbar.CollapsingToolbar
import me.onebone.toolbar.CollapsingToolbarScope
import me.onebone.toolbar.CollapsingToolbarState
import me.onebone.toolbar.rememberCollapsingToolbarState

@Composable
internal fun CollapsingToolbarScope.TopAppBar(
    state: CollapsingToolbarState,
    wheel: FeatureWheel,
    modifier: Modifier = Modifier
) {
    val titleTextStyle = SelfTheme.text.headline

    Spacer(Modifier.height(56.dp))

    Box(
        modifier
            .pin()
            .background(SelfTheme.colors.elevated)
            .height(128.dp)
            .fillMaxWidth()
    )

    Text(
        wheel.name,
        Modifier
            .road(
                whenCollapsed = Alignment.CenterStart,
                whenExpanded = Alignment.BottomStart
            )
            .padding(SelfTheme.sizes.spacing.large)
            .fillMaxSize(),
        SelfTheme.colors.text.highlighted,
        fontSize = state.fontSize(
            collapsed = SelfTheme.text.title.large.fontSize,
            expanded = titleTextStyle.fontSize
        ),
        style = titleTextStyle
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TopAppBarPreview() {
    SelfTheme {
        CollapsingToolbar(collapsingToolbarState = rememberCollapsingToolbarState()) {
            TopAppBar(rememberCollapsingToolbarState(), FeatureWheel.sample)
        }
    }
}
