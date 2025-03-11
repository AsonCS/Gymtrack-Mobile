package br.com.asoncsts.multi.gymtrack.ui.exerciseDetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.capitalized
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.ui._components.*
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.getWidthDp
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

internal data class ExerciseDetailScreenProps(
    val exercise: Exercise.Detail?,
    val placeholder: Painter
)

@Composable
internal fun exerciseDetailScreenProps(
    exercise: Exercise.Detail?
) = ExerciseDetailScreenProps(
    exercise = exercise,
    placeholder = painterResource(Res.drawable.logo)
)

@Composable
internal fun ExerciseDetailScreen(
    props: ExerciseDetailScreenProps,
    modifier: Modifier = Modifier
) {
    if (props.exercise == null) {
        Loading(modifier)
        return
    }

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                16.dp
            )
    ) {
        ImageWithCache(
            contentDescription = props.exercise.title,
            imageUrl = props.exercise.image,
            placeholder = props.placeholder,
            ratio = Ratio.Wide,
            width = getWidthDp(),
        )

        Text(
            props.exercise.title.capitalizedWords(),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().displayLarge
        )

        Text(
            props.exercise.description.capitalized(),
            color = colors().onBackground
                .copy(.7f),
            style = typography().bodyLarge
        )
    }
}
