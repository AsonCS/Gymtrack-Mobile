package br.com.asoncsts.multi.gymtrack.ui._app

data class AppTopBarState(
    val handlerBack: (() -> Unit)? = null,
    val showUser: Boolean = false,
    val showTopBar: Boolean = handlerBack != null
            || showUser
)
