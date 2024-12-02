package br.com.asoncsts.multi.gymtrack

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import br.com.asoncsts.multi.gymtrack._mock.AuthRepositoryMock
import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepository
import br.com.asoncsts.multi.gymtrack.ui._app.App
import org.koin.dsl.module

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gymtrack",
        state = WindowState(
            size = DpSize(
                height = 915.dp,
                width = 412.dp
            )
        )
    ) {
        App(
            platformModule = module {
                single<AuthRepository> {
                    AuthRepositoryMock
                }
            }
        )
    }
}
