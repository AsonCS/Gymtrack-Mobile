package br.com.asoncsts.multi.gymtrack.ui.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack._mock.data.exercise.ExerciseMock
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.ui._theme.*
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

interface ExerciseProps {
    @Composable
    fun placeholder(): Painter = painterResource(Res.drawable.logo)
}

@Composable
fun Exercise(
    exercise: Exercise,
    modifier: Modifier = Modifier,
    props: ExerciseProps = object : ExerciseProps {}
) {
    ElevatedCard(
        modifier,
        shape = shapes().extraSmall
    ) {
        Row(
            Modifier
                .padding(6.dp),
            horizontalArrangement = Arrangement
                .spacedBy(
                    12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageRequest = koinInject<ImageRequest> {
                parametersOf(
                    "${exercise.image}&height=100&width=100"
                )
            }
            AsyncImage(
                imageRequest,
                exercise.title,
                Modifier
                    .size(100.dp)
                    .clip(shapes().extraSmall),
                contentScale = ContentScale.Crop,
                error = props.placeholder(),
                placeholder = props.placeholder()
            )
            val locale = Locale.current
            Column(
                Modifier
                    .weight(1f)
            ) {
                Text(
                    exercise.title.capitalizedWords(locale),
                    color = colors().onBackground,
                    fontWeight = FontWeight.Bold,
                    style = typography().titleLarge
                )
            }
        }
    }
}

internal val exerciseValuesProvider = sequenceOf(
    *(ExerciseMock.exerciseDetails.toTypedArray())
)
