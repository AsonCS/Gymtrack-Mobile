package br.com.asoncsts.multi.gymtrack.ui.exerciseDetail

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import br.com.asoncsts.multi.gymtrack.model.exercise.Exercise
import br.com.asoncsts.multi.gymtrack.ui._navigation.ExerciseDetailDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.exerciseDetail.components.ExerciseDetailScreen
import br.com.asoncsts.multi.gymtrack.ui.exerciseDetail.components.exerciseDetailScreenProps
import org.koin.compose.koinInject

@Composable
fun ExerciseDetailScreen(
    alias: String,
    args: Args,
    modifier: Modifier = Modifier,
    repository: ExerciseRepository = koinInject()
) {
    var exercise by remember {
        mutableStateOf<Exercise.Detail?>(
            null
        )
    }

    ExerciseDetailScreen(
        exerciseDetailScreenProps(
            exercise
        ),
        modifier
    )

    LaunchedEffect(Unit) {
        when (val result = repository.getExercise(alias)) {
            is Wrapper.Error -> args.navigateUp()
            is Wrapper.Success -> {
                exercise = result.data
            }
        }
    }
}
