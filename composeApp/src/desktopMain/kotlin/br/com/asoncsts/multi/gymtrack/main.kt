package br.com.asoncsts.multi.gymtrack

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gymtrack",
    ) {
        App()
    }
}