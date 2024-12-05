package br.com.asoncsts.multi.gymtrack.ui.home

sealed class HomeState {
    data object Loading : HomeState()
}
