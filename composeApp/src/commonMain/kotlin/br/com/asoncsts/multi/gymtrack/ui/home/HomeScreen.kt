package br.com.asoncsts.multi.gymtrack.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.ui._navigation.HomeScreenDestination.Args
import br.com.asoncsts.multi.gymtrack.ui._theme.typography

@Composable
fun HomeScreen(
    args: Args,
    modifier: Modifier = Modifier
) {
    HomeScreen(
        modifier = modifier,
        props = HomeProps(),
        state = HomeState.Loading
    )

    LaunchedEffect(Unit) {
        args.appViewModel.stateTopBarUpdate(
            showUser = true
        )
    }
}

@Composable
internal fun HomeScreen(
    modifier: Modifier,
    props: HomeProps,
    state: HomeState
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Home Screen",
            style = typography().titleSmall
        )
    }
}
