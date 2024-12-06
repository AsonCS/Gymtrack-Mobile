package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone
import br.com.asoncsts.multi.gymtrack.ui.home.HomeState

@PreviewMedPhone
@Composable
private fun Preview(
    @PreviewParameter(HomeStateValuesProvider::class) state: HomeState
) {
    PreviewContainer(
        Modifier
            .padding(16.dp)
    ) {
        HomeScreen(
            homeScreenProps(
                labelTitle = stringResource(
                    R.string.home_label_title
                )
            ),
            state
        )
    }
}

private class HomeStateValuesProvider : PreviewParameterProvider<HomeState> {
    override val values = homeStateValuesProvider
}
