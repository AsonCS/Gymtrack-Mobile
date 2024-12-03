package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer
import br.com.asoncsts.multi.gymtrack.ui.PreviewMedPhone
import br.com.asoncsts.multi.gymtrack.ui.home.HomeState
import br.com.asoncsts.multi.gymtrack.ui.home.homeStateValuesProvider

@PreviewMedPhone
@Composable
private fun Preview(
    @PreviewParameter(HomeStateValuesProvider::class) state: HomeState
) {
    PreviewContainer {
        HomeScreen(
            Modifier,
            homeScreenProps(),
            state
        )
    }
}

@Composable
private fun homeScreenProps() = HomeScreenProps(
    onExerciseClick = {},
    title = stringResource(R.string.home_title)
)

private class HomeStateValuesProvider : PreviewParameterProvider<HomeState> {
    override val values = homeStateValuesProvider
}
