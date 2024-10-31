package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.data.auth.model.AuthState.LoggedIn
import br.com.asoncsts.multi.gymtrack.ui.component.UserIcon

@Composable
fun AppTopBar(
    appViewModel: AppViewModel,
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
            handlerUser = stateTopBar.handlerUser,
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
                        .padding(4.dp)
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
