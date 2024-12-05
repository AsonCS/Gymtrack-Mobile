package br.com.asoncsts.multi.gymtrack.ui.exerciseDetail

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.model.Exercise
import br.com.asoncsts.multi.gymtrack.data.exercise.repository.ExerciseRepository
import br.com.asoncsts.multi.gymtrack.ui.BackHandlerContainer
import br.com.asoncsts.multi.gymtrack.ui._navigation.ExerciseDetailDestination.Args
import br.com.asoncsts.multi.gymtrack.ui.exerciseDetail.components.ExerciseDetailScreen
import br.com.asoncsts.multi.gymtrack.ui.exerciseDetail.components.exerciseDetailScreenProps
import org.koin.compose.koinInject

const val TAG_EXERCISE_DETAIL = "gymtrack:exerciseDetail"

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

    BackHandlerContainer(
        args.navigateUp,
        modifier
    ) {
        ExerciseDetailScreen(
            exerciseDetailScreenProps(
                exercise
            )
        )
    }

    LaunchedEffect(Unit) {
        when (val result = repository.getExercise(alias)) {
            is Wrapper.Error -> args.navigateUp()
            is Wrapper.Success -> {
                exercise = result.data
            }
        }
    }
}
