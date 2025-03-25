package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors

@Composable
fun Button(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        onClick,
        modifier,
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = colors().onBackground
        )
    ) {
        Text(
            label,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ButtonAdd(
    label: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonIcon(
        "Add icon",
        Icons.Filled.Add,
        label,
        onClick,
        modifier
    )
}

@Composable
fun ButtonCheck(
    label: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonIcon(
        "Check icon",
        Icons.Filled.Check,
        label,
        onClick,
        modifier
    )
}

@Composable
fun ButtonClear(
    label: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonIcon(
        "Clear icon",
        Icons.Filled.Clear,
        label,
        onClick,
        modifier
    )
}

@Composable
fun ButtonDelete(
    label: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonIcon(
        "Delete icon",
        Icons.Filled.Delete,
        label,
        onClick,
        modifier
    )
}

@Composable
fun ButtonEdit(
    label: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonIcon(
        "Edit icon",
        Icons.Filled.Edit,
        label,
        onClick,
        modifier
    )
}

@Composable
private fun ButtonIcon(
    contentDescription: String,
    imageVector: ImageVector,
    label: String?,
    onClick: () -> Unit,
    modifier: Modifier
) {
    ElevatedButton(
        onClick,
        modifier,
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = colors().onBackground
        )
    ) {
        Icon(
            imageVector,
            contentDescription
        )

        if (label != null) {
            Spacer(
                Modifier
                    .width(8.dp)
            )

            Text(
                label,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
