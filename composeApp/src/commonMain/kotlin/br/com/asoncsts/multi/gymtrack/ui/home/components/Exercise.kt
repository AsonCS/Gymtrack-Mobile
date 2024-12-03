package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.data.exercise.model.Exercise
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography

@Composable
fun Exercise(
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    here
    Text(
        exercise.title,
        color = colors().primary,
        style = typography().titleSmall
    )
}
