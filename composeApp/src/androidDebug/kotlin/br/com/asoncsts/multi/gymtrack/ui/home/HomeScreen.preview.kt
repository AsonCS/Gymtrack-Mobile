package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone

@PreviewMedPhone
@Composable
internal fun HomeScreenPreview(
    @PreviewParameter(ValuesProvider::class) state: HomeState
) {
    PreviewContainer {
        HomeScreen(
            Modifier,
            HomeProps(),
            state
        )
    }
}

private class ValuesProvider : PreviewParameterProvider<HomeState> {
    override val values = homeStateValuesProvider
}
