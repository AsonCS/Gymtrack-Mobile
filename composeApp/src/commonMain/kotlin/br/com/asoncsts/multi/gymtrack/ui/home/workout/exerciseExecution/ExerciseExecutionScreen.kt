package br.com.asoncsts.multi.gymtrack.ui.home.workout.exerciseExecution

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.extension.capitalizedWords
import br.com.asoncsts.multi.gymtrack.ui._components.LoadingBox
import br.com.asoncsts.multi.gymtrack.ui._components.ScreenTopBar
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.execution.*
import br.com.asoncsts.multi.gymtrack.model.exercise.Execution as ModelExecution

@Composable
internal fun ExerciseExecutionScreen(
    onExecutionConfirm: () -> Unit,
    onExecutionFinish: (
        ModelExecution
    ) -> Unit,
    onExecutionRemove: (
        executionId: String
    ) -> Unit,
    onExecutionToggleDialog: (
        ModelExecution?
    ) -> Unit,
    onNavigateUp: () -> Unit,
    state: ExerciseExecutionState,
    stateFields: EditStateFields,
    modifier: Modifier = Modifier
) {
    when (state) {
        is ExerciseExecutionState.Error -> {
            Text(
                state.throwable.message
                    ?: "Error",
                Modifier
                    .fillMaxSize(),
                color = colors().error,
                style = typography().titleSmall
            )
        }

        ExerciseExecutionState.Loading -> {
            LoadingBox(
                Modifier
                    .fillMaxSize()
            )
        }

        is ExerciseExecutionState.Success -> Success(
            onExecutionConfirm = onExecutionConfirm,
            onExecutionFinish = onExecutionFinish,
            onExecutionRemove = onExecutionRemove,
            onExecutionToggleDialog = onExecutionToggleDialog,
            onNavigateUp = onNavigateUp,
            state,
            stateFields,
            modifier
        )
    }
}

@Composable
private fun Success(
    onExecutionConfirm: () -> Unit,
    onExecutionFinish: (
        ModelExecution
    ) -> Unit,
    onExecutionRemove: (
        executionId: String
    ) -> Unit,
    onExecutionToggleDialog: (
        ModelExecution?
    ) -> Unit,
    onNavigateUp: () -> Unit,
    state: ExerciseExecutionState.Success,
    stateFields: EditStateFields,
    modifier: Modifier
) {
    val locale = Locale.current
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(8.dp)
    ) {
        val exerciseTitle = state
            .exerciseExecution
            .exercise
            ?.title

        if (exerciseTitle != null) {
            ScreenTopBar(
                onNavigateUp,
                exerciseTitle.capitalizedWords(),
                Modifier
                    .padding(
                        horizontal = 16.dp
                    )
            )

            HorizontalDivider()
        }

        ScreenTopBar(
            if (exerciseTitle != null)
                null
            else
                onNavigateUp,
            state.exerciseExecution.name,
            Modifier
                .padding(
                    horizontal = 16.dp
                )
        )

        val description = state.exerciseExecution.description
        if (!description.isNullOrEmpty()) {
            Text(
                description.capitalize(locale),
                Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = colors().onBackground
                    .copy(.7f),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = typography().bodySmall
            )
        }

        EditExecution(
            onConfirm = {
                onExecutionConfirm()
            },
            onRemove = { executionId ->
                onExecutionRemove(executionId)
            },
            onToggleDialog = {
                onExecutionToggleDialog(null)
            },
            stateFields,
            Modifier
        )

        HorizontalDivider()

        LazyColumn(
            Modifier
                .weight(1f)
        ) {
            items(
                items = state.exerciseExecution.executions,
                key = { it.id }
            ) { execution ->
                Execution(
                    execution,
                    onFinish = onExecutionToggleDialog,
                    Modifier
                        .clickable {
                            onExecutionToggleDialog(execution)
                        }
                )
            }
        }
    }
}
