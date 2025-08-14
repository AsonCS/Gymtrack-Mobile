package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import gymtrack.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

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
        painterResource(Res.drawable.add),
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
        painterResource(Res.drawable.check),
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
        painterResource(Res.drawable.clear),
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
        painterResource(Res.drawable.delete),
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
        painterResource(Res.drawable.edit),
        label,
        onClick,
        modifier
    )
}

@Composable
private fun ButtonIcon(
    contentDescription: String,
    painter: Painter,
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
            painter,
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
