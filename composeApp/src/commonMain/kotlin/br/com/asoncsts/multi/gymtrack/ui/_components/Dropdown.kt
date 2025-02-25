@file:OptIn(ExperimentalFoundationApi::class)

package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._theme.shapes

@Composable
fun <Item> Dropdown(
    dialogOpen: Boolean,
    items: List<Item>,
    itemFilter: (Item, String) -> Boolean,
    itemKey: (Item) -> String,
    itemText: (Item) -> String,
    itemUpdate: (Item) -> Unit,
    label: String,
    onCloseDialog: () -> Unit,
) {
    var itemsFiltered by remember {
        mutableStateOf(items)
    }
    var filterText by remember {
        mutableStateOf("")
    }

    if (dialogOpen) {
        Dialog(
            filterText = filterText,
            items = itemsFiltered,
            itemKey = itemKey,
            itemText = itemText,
            label = label,
            onDismissRequest = {
                onCloseDialog()
                filterText = ""
            },
            onFilterTextChange = { value ->
                itemsFiltered = items.filter {
                    itemFilter(it, value)
                }
                filterText = value
            },
            onItemClick = {
                itemUpdate(it)
                onCloseDialog()
                filterText = ""
            }
        )
    }
}

@Composable
fun <Item> DropdownField(
    item: Item?,
    items: List<Item>,
    itemFilter: (Item, String) -> Boolean,
    itemKey: (Item) -> String,
    itemText: (Item) -> String,
    itemUpdate: (Item) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    Field(
        item
            ?.let(itemText)
            ?: placeholder,
        label,
        modifier
            .clickable {
                dialogOpen = true
            }
    )

    Dropdown(
        dialogOpen,
        items,
        itemFilter,
        itemKey,
        itemText,
        itemUpdate,
        label,
        onCloseDialog = {
            dialogOpen = false
        }
    )
}

@Composable
fun <Item> Dialog(
    filterText: String,
    items: List<Item>,
    itemKey: (Item) -> String,
    itemText: (Item) -> String,
    label: String,
    onDismissRequest: () -> Unit,
    onFilterTextChange: (String) -> Unit,
    onItemClick: (Item) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        OutlinedCard(
            Modifier
                .fillMaxWidth(),
            border = border(),
            shape = shapes().medium
        ) {
            LazyColumn(
                Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement
                    .spacedBy(
                        alignment = Alignment.CenterVertically,
                        space = 16.dp
                    )
            ) {
                stickyHeader {
                    TextField(
                        KeyboardType.Text,
                        label,
                        onFilterTextChange,
                        label,
                        filterText,
                        Modifier
                            .fillMaxWidth(),
                        trailingIcon = {
                            Icon(
                                Icons.Filled.Search,
                                "Search"
                            )
                        }
                    )
                }

                items(
                    items = items,
                    key = { itemKey(it) }
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(it)
                            }
                    ) {
                        Text(
                            itemText(it)
                        )

                        HorizontalDivider(
                            Modifier
                                .padding(
                                    top = 4.dp
                                ),
                            color = colors().onBackground
                                .copy(
                                    alpha = .5f
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Field(
    item: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(
                vertical = 6.dp
            )
            .height(64.dp)
    ) {
        Box(
            Modifier
                .matchParentSize()
                .border(
                    border(),
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
private fun border() = BorderStroke(
    1.dp,
    colors().onBackground
)
