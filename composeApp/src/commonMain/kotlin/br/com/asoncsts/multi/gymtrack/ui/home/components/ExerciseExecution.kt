package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.ui._theme.*

internal data class ExerciseExecutionProps(
    val exercise: ExerciseExecution,
    val locale: String
)

@Composable
internal fun ExerciseExecution(
    props: ExerciseExecutionProps,
    modifier: Modifier = Modifier
) {
    val locale = Locale.current

    ElevatedCard(
        modifier,
        shape = shapes().extraSmall
    ) {
        Text(
            props.exercise.name.capitalizedWords(locale),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().titleLarge
        )
    }
}
