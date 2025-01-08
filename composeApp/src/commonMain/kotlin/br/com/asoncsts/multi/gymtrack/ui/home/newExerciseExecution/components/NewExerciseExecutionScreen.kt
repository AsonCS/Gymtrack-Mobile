package br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._components.*
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.typography
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.NewExerciseExecutionState
import br.com.asoncsts.multi.gymtrack.ui.home.newExerciseExecution.NewExerciseExecutionStateFields
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

internal data class NewExerciseExecutionScreenProps(
    val labelDescription: String,
    val labelExercise: String,
    val labelName: String,
    val labelNewExerciseExecution: String,
    val navigateUp: () -> Unit,
    val onSave: () -> Unit
)

@Composable
internal fun newExerciseExecutionScreenProps(
    navigateUp: () -> Unit,
    onSave: () -> Unit,
    labelDescription: String = stringResource(
        Res.string.label_description
    ),
    labelExercise: String = stringResource(
        Res.string.new_exercise_execution_label_exercise
    ),
    labelName: String = stringResource(
        Res.string.label_name
    ),
    labelNewExerciseExecution: String = stringResource(
        Res.string.new_exercise_execution_label_new
    )
) = NewExerciseExecutionScreenProps(
    labelDescription,
    labelExercise,
    labelName,
    labelNewExerciseExecution,
    navigateUp,
    onSave
)

@Composable
internal fun NewExerciseExecutionScreen(
    props: NewExerciseExecutionScreenProps,
    state: NewExerciseExecutionState,
    stateFields: NewExerciseExecutionStateFields,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Text(
            props.labelNewExerciseExecution,
            Modifier
                .fillMaxWidth(),
            color = colors().onBackground,
            fontWeight = FontWeight.Bold,
            style = typography().headlineLarge,
            textAlign = TextAlign.Center
        )

        when (state) {
            is NewExerciseExecutionState.Error -> {
                Text(
                    state.throwable.message
                        ?: "Error",
                    Modifier
                        .weight(1f),
                    color = colors().error,
                    style = typography().titleSmall
                )
            }

            NewExerciseExecutionState.Loading -> {
                Box(
                    Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Loading()
                }
            }

            is NewExerciseExecutionState.Success -> {
                Success(props, stateFields)
            }

            is NewExerciseExecutionState.SuccessNewExerciseExecution -> {
                props.navigateUp()
            }
        }
    }
}

@Composable
private fun Success(
    props: NewExerciseExecutionScreenProps,
    stateFields: NewExerciseExecutionStateFields
) {
    TextField(
        KeyboardType.Text,
        props.labelName,
        stateFields::updateName,
        props.labelName,
        stateFields.name
            ?: "",
        Modifier
            .fillMaxWidth(),
        capitalization = KeyboardCapitalization.Words
    )

    TextField(
        KeyboardType.Text,
        props.labelDescription,
        stateFields::updateDescription,
        props.labelDescription,
        stateFields.description
            ?: "",
        Modifier
            .fillMaxWidth(),
        capitalization = KeyboardCapitalization.Sentences,
        onDone = {
            props.onSave()
        }
    )

    Dropdown(
        props.labelExercise,
        Modifier
            .fillMaxWidth()
    )

}
