package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.ui._app.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal fun uiModule() = module {
    viewModel<AppViewModel> {
        AppViewModel.Impl()
    }
}
