package br.com.asoncsts.multi.gymtrack.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.data._utils.Wrapper
import br.com.asoncsts.multi.gymtrack.data.userExercise.repository.UserExerciseRepository
import br.com.asoncsts.multi.gymtrack.extension.log
import br.com.asoncsts.multi.gymtrack.ui.home.HomeState
import br.com.asoncsts.multi.gymtrack.ui.home.TAG_HOME
import org.koin.compose.koinInject

@Composable
internal fun HomeScreen(
    state: HomeState,
    modifier: Modifier = Modifier,
    repository: UserExerciseRepository = koinInject()
) {
    Box {
        Text(
            "Home Screen"
        )
    }

    LaunchedEffect(Unit) {
        val exercises = (repository.getUserExercises() as Wrapper.Success).data
        TAG_HOME.log("UserExercises: $exercises")
        val exercise = (repository.getUserExercise(exercises[1].id) as Wrapper.Success).data
        TAG_HOME.log("UserExercise: $exercise")
    }
}
