package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModel
import br.com.asoncsts.multi.gymtrack.ui.auth.AuthViewModelImpl
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModel
import br.com.asoncsts.multi.gymtrack.ui.search.SearchViewModelImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal fun uiModule() = module {
    viewModel<AppViewModel> {
        AppViewModelImpl()
    }
    viewModel<AuthViewModel> {
        AuthViewModelImpl(
            auth = get()
        )
    }
    viewModel<SearchViewModel> {
        SearchViewModelImpl(
            repository = get()
        )
    }
}
