package br.com.asoncsts.multi.gymtrack.ui._app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncsts.multi.gymtrack.data.auth.model.User
import br.com.asoncsts.multi.gymtrack.ui._components.UserIcon

internal data class AppBottomBarProps(
    val navigateToHome: () -> Unit,
    val navigateToLogin: () -> Unit,
    val navigateToSearch: () -> Unit,
    val navigateToUser: () -> Unit,
    val user: User?
)

@Composable
internal fun appBottomBarProps(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToUser: () -> Unit,
    user: User?
) = AppBottomBarProps(
    navigateToHome,
    navigateToLogin,
    navigateToSearch,
    navigateToUser,
    user
)

@Composable
internal fun AppBottomBar(
    props: AppBottomBarProps,
    modifier: Modifier = Modifier
) {
    AppBottomBar(
        hasHome = props.user != null,
        onClickHome = props.navigateToHome,
        onClickProfile = if (props.user == null)
            props.navigateToLogin
        else
            props.navigateToUser,
        onClickSearch = props.navigateToSearch,
        userDisplayName = props.user
            ?.displayName,
        userPhotoUrl = props.user
            ?.photoUrl,
        modifier = modifier
    )
}

@Composable
internal fun AppBottomBar(
    hasHome: Boolean,
    onClickHome: () -> Unit,
    onClickProfile: () -> Unit,
    onClickSearch: () -> Unit,
    userDisplayName: String?,
    userPhotoUrl: String?,
    modifier: Modifier = Modifier
) {
    val size = 56.dp

    BottomAppBar(
        actions = {
            if (hasHome) {
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
            }

            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClickSearch) {
                    Icon(
                        Icons.Filled.Search,
                        "Search",
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
