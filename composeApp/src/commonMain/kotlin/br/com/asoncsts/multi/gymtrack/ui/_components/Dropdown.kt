package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.asoncsts.multi.gymtrack.ui._theme.colors

@Composable
fun Dropdown(
    label: String,
    modifier: Modifier = Modifier
) {
    val original = (0..10).map {
        "Item $it"
    }
    var items by remember {
        mutableStateOf(original)
    }
    var item by remember {
        mutableStateOf(items[0])
    }

    here
    Box(
        modifier
            .padding(
                vertical = 6.dp
            )
            .height(64.dp)
            .clickable {}
    ) {
        Box(
            Modifier
                .matchParentSize()
                .border(
                    BorderStroke(
                        1.dp,
                        colors().onBackground
                    ),
                    textFieldShape()
                )
                .padding(
                    start = 18.dp
                )
        ) {
            Text(
                item,
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
            Icon(
                Icons.Filled.ArrowDropDown,
                label,
                Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp)
            )
        }
        Text(
            label,
            Modifier
                .offset(
                    x = 14.dp,
                    y = (-12).dp
                )
                .align(Alignment.TopStart)
                .background(colors().background)
                .padding(
                    horizontal = 4.dp
                ),
            fontSize = 12.sp
        )
    }
}

@Composable
fun DropdownOld(
    modifier: Modifier = Modifier
) {
    Box(
        modifier
    ) {
        val original = (0..10).map {
            "Item $it"
        }
        var items by remember {
            mutableStateOf(original)
        }
        var item by remember {
            mutableStateOf("")
        }
        var expanded by remember {
            mutableStateOf(false)
        }

        TextField(
            keyboardType = KeyboardType.Text,
            label = "Dropdown",
            onValueChange = {
                item = it
                items = original.filter { it.contains(it) }
                expanded = true
            },
            placeholder = "Dropdown",
            value = item,
            Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        Icon(
            Icons.Filled.ArrowDropDown,
            "Dropdown",
            Modifier
                .background(Color.Red)
                .padding(
                    horizontal = 10.dp,
                    vertical = 4.dp
                )
                .align(Alignment.CenterEnd)
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded,
            onDismissRequest = {
                expanded = false
            },
            Modifier
                .fillMaxWidth()
        ) {
            items.forEachIndexed { idx, it ->
                DropdownMenuItem(
                    text = {
                        Text(it)
                    },
                    onClick = {
                        item = items[idx]
                    }
                )
            }
        }
    }
}
