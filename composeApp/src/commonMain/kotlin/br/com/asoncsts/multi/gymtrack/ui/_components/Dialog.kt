package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.shapes
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditDialog(
    buttonNegative: Pair<String, () -> Unit>?,
    buttonPositive: Pair<String, () -> Unit>?,
    onDismissRequest: () -> Unit,
    isDialogVisible: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {
    if (isDialogVisible.not()) return

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismissRequest
    ) {
        OutlinedCard(
            Modifier
                .fillMaxWidth(),
            border = border(),
            shape = shapes().medium
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(16.dp)
            ) {
                content()

                Row(
                    horizontalArrangement = Arrangement
                        .spacedBy(8.dp)
                ) {
                    buttonNegative?.let {
                        Button(
                            buttonNegative.first,
                            onClick = buttonNegative.second
                        )
                    }
                    buttonPositive?.let {
                        Button(
                            buttonPositive.first,
                            onClick = buttonPositive.second
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun DeleteConfirmDialog(
    isVisible: Boolean,
    onDelete: () -> Unit,
    onDismissRequest: () -> Unit
) {
    if (isVisible.not()) return

    AlertDialog(
        confirmButton = {
            Button(
                stringResource(
                    Res.string.label_remove
                ),
                onClick = onDelete
            )
        },
        dismissButton = {
            Button(
                stringResource(
                    Res.string.label_cancel
                ),
                onClick = onDismissRequest
            )
        },
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                stringResource(
                    Res.string.label_remove
                )
            )
        }
    )
}
