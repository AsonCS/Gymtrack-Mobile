package br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import br.com.asoncsts.multi.gymtrack.extension.launch
import br.com.asoncsts.multi.gymtrack.ui.BackHandlerContainer
import br.com.asoncsts.multi.gymtrack.ui._navigation.home.NewExerciseExecutionArgs
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.components.NewExerciseExecutionScreen
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.components.newExerciseExecutionScreenProps
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NewExerciseExecutionScreen(
    args: NewExerciseExecutionArgs,
    modifier: Modifier = Modifier,
    viewModel: NewExerciseExecutionViewModel = koinViewModel {
        parametersOf(
            args.exercisesSource
        )
    }
) {
    val state by viewModel
        .state
        .collectAsState()
    val stateFields by viewModel
        .stateFields
        .collectAsState()

    BackHandlerContainer(
        args.navigateUp,
        modifier
    ) {
        NewExerciseExecutionScreen(
            newExerciseExecutionScreenProps(
                navigateUp = args.navigateUp,
                onSave = {
                    viewModel.launch {
                        save()
                    }
                }
            ),
            state,
            stateFields,
            modifier
        )
    }

    LaunchedEffect(Unit) {
        viewModel.launch {
            getExercises()
        }
    }
}

abstract class NewExerciseExecutionViewModel : ViewModel() {
    internal abstract val state: StateFlow<NewExerciseExecutionState>
    internal abstract val stateFields: StateFlow<NewExerciseExecutionStateFields>

    internal abstract suspend fun getExercises()

    internal abstract suspend fun save()
}
