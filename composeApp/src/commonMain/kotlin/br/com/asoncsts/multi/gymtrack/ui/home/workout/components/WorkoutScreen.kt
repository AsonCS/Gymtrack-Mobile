package br.com.asoncsts.multi.gymtrack.ui.home.workout.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack._mock.data.user.exerciseExecution.ExerciseExecutionMock
import br.com.asoncsts.multi.gymtrack.model.exercise.ExerciseExecution
import br.com.asoncsts.multi.gymtrack.model.workout.Workout
import br.com.asoncsts.multi.gymtrack.ui.Toast
import br.com.asoncsts.multi.gymtrack.ui._components.*
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutShared
import br.com.asoncsts.multi.gymtrack.ui.home.workout.WorkoutState
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

internal data class WorkoutScreenProps(
    val addNewExerciseExecution: (
        ExerciseExecution
    ) -> Unit,
    val labelAddExerciseExecution: String,
    val labelNewExerciseExecution: String,
    val navigateUp: () -> Unit,
    val navigateToExerciseExecution: (
        id: String
    ) -> Unit,
    val navigateToNewExerciseExecution: () -> Unit,
    val toastAddNewExerciseExecutionError: String,
    val toastAddNewExerciseExecutionSuccess: String,
    val workout: Workout
)

@Composable
internal fun workoutScreenProps(
    addNewExerciseExecution: (
        ExerciseExecution
    ) -> Unit,
    navigateUp: () -> Unit,
    navigateToExerciseExecution: (
        id: String
    ) -> Unit,
    navigateToNewExerciseExecution: () -> Unit,
    workout: Workout,
    labelAddExerciseExecution: String = stringResource(
        Res.string.workout_label_add_exercise_execution
    ),
    labelNewExerciseExecution: String = stringResource(
        Res.string.workout_label_new_exercise_execution
    ),
    toastAddNewExerciseExecutionError: String = stringResource(
        Res.string.toast_insert_error
    ),
    toastAddNewExerciseExecutionSuccess: String = stringResource(
        Res.string.workout_toast_add_exercise_execution_success
    )
) = WorkoutScreenProps(
    addNewExerciseExecution,
    labelAddExerciseExecution,
    labelNewExerciseExecution,
    navigateUp,
    navigateToExerciseExecution,
    navigateToNewExerciseExecution,
    toastAddNewExerciseExecutionError,
    toastAddNewExerciseExecutionSuccess,
    workout
)

@Composable
internal fun WorkoutScreen(
    props: WorkoutScreenProps,
    shared: WorkoutShared?,
    state: WorkoutState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(16.dp)
    ) {
        ScreenTopBar(
            props.navigateUp,
            props.workout.name
        )

        when (shared) {
            is WorkoutShared.ErrorInit -> {
                Toast(
                    "Error: ${shared.message}",
                    true
                )
            }

            is WorkoutShared.ErrorAddNewExerciseExecution -> {
                Toast(
                    "Error: ${props.toastAddNewExerciseExecutionError}",
                    true
                )
            }

            is WorkoutShared.SuccessAddNewExerciseExecution -> {
                Toast(
                    props.toastAddNewExerciseExecutionSuccess,
                    true
                )
            }

            null -> {}
        }

        when (state) {
            WorkoutState.Loading -> {
                Box(
                    Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Loading()
                }
            }

            is WorkoutState.Success -> {
                Success(
                    props,
                    state,
                    Modifier
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
private fun Success(
    props: WorkoutScreenProps,
    state: WorkoutState.Success,
    modifier: Modifier = Modifier
) {
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    Dropdown(
        dialogOpen = dialogOpen,
        items = state.allExerciseExecutions,
        itemFilter = { item, filter ->
            item.name.contains(filter)
        },
        itemKey = {
            it.id
        },
        itemText = {
            it.name
        },
        itemUpdate = props.addNewExerciseExecution,
        label = props.labelAddExerciseExecution,
        onCloseDialog = {
            dialogOpen = false
        }
    )

    LazyColumn(
        modifier,
        verticalArrangement = Arrangement
            .spacedBy(8.dp)
    ) {
        items(
            items = state.filteredExerciseExecutions,
            key = { it.id }
        ) { exerciseExecution ->
            ExerciseExecution(
                exerciseExecutionProps(
                    exerciseExecution,
                    navigateToExerciseExecution = {
                        props.navigateToExerciseExecution(
                            exerciseExecution.id
                        )
                    }
                ),
                Modifier
                    .fillMaxWidth()
            )
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                NewElementButton(
                    props.labelAddExerciseExecution,
                    {
                        dialogOpen = true
                    }
                )
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                NewElementButton(
                    props.labelNewExerciseExecution,
                    props.navigateToNewExerciseExecution
                )
            }
        }
    }
}

internal fun workoutScreenStateSequence() = sequenceOf(
    WorkoutState.Loading,
    WorkoutState.Success(
        ExerciseExecutionMock.exerciseExecutions,
        ExerciseExecutionMock.exerciseExecutions
    )
)
