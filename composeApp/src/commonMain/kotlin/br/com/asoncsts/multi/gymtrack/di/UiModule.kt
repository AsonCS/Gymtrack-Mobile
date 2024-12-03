package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModel
import br.com.asoncsts.multi.gymtrack.ui.home.HomeViewModelImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal fun uiModule() = module {
    viewModel<AppViewModel> {
        AppViewModel.Impl()
    }
    viewModel<AuthViewModel> {
        AuthViewModel.Impl(
            auth = get()
        )
    }
    viewModel<HomeViewModel> {
        HomeViewModelImpl(
            repository = get()
        )
    }
}
