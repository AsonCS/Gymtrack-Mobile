package br.com.asoncsts.multi.gymtrack.ui._app

data class AppTopBarState(
    val handlerBack: (() -> Unit)? = null,
    val handlerUser: (() -> Unit)? = null,
    val showTopBar: Boolean = handlerBack != null
            || handlerUser != null
)
