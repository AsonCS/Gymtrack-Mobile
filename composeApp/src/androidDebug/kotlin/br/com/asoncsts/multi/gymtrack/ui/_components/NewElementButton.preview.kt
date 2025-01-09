package br.com.asoncsts.multi.gymtrack.ui._components

import androidx.compose.runtime.Composable
import br.com.asoncsts.multi.gymtrack.ui.PreviewComponent
import br.com.asoncsts.multi.gymtrack.ui.PreviewContainer

@PreviewComponent
@Composable
private fun Preview() {
    PreviewContainer {
        NewElementButton(
            "New",
            {}
        )
    }
}
