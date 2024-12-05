package br.com.asoncsts.multi.gymtrack.ui.search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.R
import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise
import br.com.asoncsts.multi.gymtrack.ui.PreviewComponent
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer

@PreviewComponent
@Composable
private fun Preview(
    @PreviewParameter(ExerciseValuesProvider::class) exercise: Exercise
) {
    PreviewContainer(
        Modifier
            .padding(16.dp)
    ) {
        Exercise(
            exercise,
            props = exerciseProps
        )
    }
}

private val exerciseProps = object : ExerciseProps {
    @Composable
    override fun placeholder() = painterResource(R.drawable.logo)
}

private class ExerciseValuesProvider : PreviewParameterProvider<Exercise> {
    override val values = exerciseValuesProvider
}
