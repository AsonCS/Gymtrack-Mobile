package br.com.asoncsts.multi.gymtrack.ui.exerciseDetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.model.Exercise
import br.com.asoncsts.multi.gymtrack.data.image.repository.ImageRepository
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.ui._components.Loading
import br.com.asoncsts.multi.gymtrack.ui._theme.*
import coil3.compose.AsyncImage
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

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
    modifier: Modifier = Modifier,
    repo: ImageRepository = koinInject()
) {
    if (props.exercise == null) {
        Loading(modifier)
        return
    }

    val locale = Locale.current

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
        AsyncImage(
            repo.image(
                props.exercise.alias,
                props.exercise.image
            ),
            props.exercise.title,
            Modifier
                .size(400.dp)
                .clip(shapes().extraSmall),
            contentScale = ContentScale.Crop,
            error = props.placeholder,
            placeholder = props.placeholder
        )

        Text(
            props.exercise.title.capitalizedWords(locale),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().displayLarge
        )

        Text(
            props.exercise.description.capitalize(locale),
            color = colors().onBackground
                .copy(.7f),
            style = typography().bodyLarge
        )
    }
}
