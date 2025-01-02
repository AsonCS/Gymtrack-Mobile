package br.com.asoncsts.multi.gymtrack.ui.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack._mock.data.exercise.ExerciseMock
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.ui._components.ImageWithCache
import br.com.asoncsts.multi.gymtrack.ui._theme.*
import gymtrack.composeapp.generated.resources.Res
import gymtrack.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

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
            ImageWithCache(
                contentDescription = exercise.title,
                imageUrl = exercise.image,
                placeholder = props.placeholder(),
                width = 100.dp
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
