package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors

@Composable
fun NewElementButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedButton(
        onClick,
        modifier,
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = colors().onBackground
        )
    ) {
        Icon(
            Icons.Filled.Add,
            "Add icon"
        )

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
