package com.jeanbarrossilva.self.feature.questionnaire.step

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.elevated

@Composable
internal fun PreviousButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick,
        modifier,
        containerColor = SelfTheme.colors.elevated,
        contentColor = SelfTheme.colors.content.secondary
    ) {
        @Suppress("SpellCheckingInspection")
        Icon(Icons.Rounded.ArrowBack, contentDescription = "Voltar")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviousButtonPreview() {
    SelfTheme {
        PreviousButton(onClick = { })
    }
}
