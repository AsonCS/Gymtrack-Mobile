package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncsts.multi.gymtrack.ui._navigation.AppNavHost

@Composable
fun AppScreen(
    appViewModel: AppViewModel,
    modifier: Modifier
) {
    Scaffold(
        modifier,
        topBar = {
            AppTopBar(appViewModel)
        }
    ) {
        AppNavHost(
            appViewModel,
            modifier
                .fillMaxSize()
        )
    }
}
