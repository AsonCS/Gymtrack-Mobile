package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedIn
import br.com.asoncsts.multi.gymtrack.ui._components.UserIcon
import br.com.asoncsts.multi.gymtrack.ui._navigation.AppNavDestination

internal data class AppBottomBarProps(
    val appViewModel: AppViewModel,
    val destinationState: State<AppNavDestination<*>>,
    val navigateToHome: () -> Unit,
    val navigateToLogin: () -> Unit,
    val navigateToUser: () -> Unit
)

@Composable
internal fun appBottomBarProps(
    appViewModel: AppViewModel,
    destinationState: State<AppNavDestination<*>>,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToUser: () -> Unit,
) = AppBottomBarProps(
    appViewModel,
    destinationState,
    navigateToHome,
    navigateToLogin,
    navigateToUser
)

@Composable
internal fun AppBottomBar(
    props: AppBottomBarProps,
    modifier: Modifier = Modifier
) {
    val destination by props.destinationState
    if (destination.hasBottomBar.not()) {
        return
    }

    val stateAuthUser by props.appViewModel
        .stateAuth
        .collectAsState()

    val user = (stateAuthUser as? LoggedIn)?.user

    AppBottomBar(
        onClickHome = props.navigateToHome,
        onClickProfile = if (user == null)
            props.navigateToLogin
        else
            props.navigateToUser,
        userDisplayName = user?.displayName,
        userPhotoUrl = user?.photoUrl,
        modifier = modifier
    )
}

@Composable
internal fun AppBottomBar(
    onClickHome: () -> Unit,
    onClickProfile: () -> Unit,
    userDisplayName: String?,
    userPhotoUrl: String?,
    modifier: Modifier = Modifier
) {
    val size = 56.dp

    BottomAppBar(
        actions = {
            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClickHome) {
                    Icon(
                        Icons.Filled.Home,
                        "Home",
                        Modifier
                            .size(size)
                    )
                }
            }

            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClickProfile) {
                    UserIcon(
                        size,
                        userDisplayName,
                        userPhotoUrl
                    )
                }
            }
        },
        modifier = modifier
    )
}
