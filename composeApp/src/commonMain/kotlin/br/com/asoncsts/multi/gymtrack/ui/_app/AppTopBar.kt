package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedIn
import br.com.asoncsts.multi.gymtrack.ui._theme.colors
import br.com.asoncsts.multi.gymtrack.ui._components.UserIcon

@Composable
fun AppTopBar(
    appViewModel: AppViewModel,
    navigateToLogin: () -> Unit,
    navigateToUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    val stateAuthUser by appViewModel.stateAuth
        .collectAsState()
    val stateTopBar by appViewModel.stateTopBar
        .collectAsState()

    if (stateTopBar.showTopBar) {
        val user = (stateAuthUser as? LoggedIn)
            ?.user
        AppTopBar(
            handlerBack = stateTopBar.handlerBack,
            handlerUser = if (stateTopBar.showUser)
                if (user == null)
                    navigateToLogin
                else
                    navigateToUser
            else null,
            modifier = modifier,
            userDisplayName = user
                ?.displayName,
            userPhotoUrl = user
                ?.photoUrl
        )
    }
}

@Composable
fun AppTopBar(
    handlerBack: (() -> Unit)?,
    handlerUser: (() -> Unit)?,
    modifier: Modifier,
    userDisplayName: String?,
    userPhotoUrl: String?
) {
    val size = 56.dp

    Row(
        modifier
            .padding(
                end = 8.dp,
                start = 8.dp,
                top = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (handlerBack != null) {
            IconButton(
                handlerBack
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    null,
                    Modifier
                        .size(size)
                        .padding(4.dp),
                    tint = colors().onBackground
                )
            }
        }

        Spacer(
            Modifier
                .weight(1f)
        )

        if (handlerUser != null) {
            IconButton(
                handlerUser
            ) {
                UserIcon(
                    size = size,
                    userName = userDisplayName,
                    userPhotoUrl = userPhotoUrl
                )
            }
        }
    }
}
