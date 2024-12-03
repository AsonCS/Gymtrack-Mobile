package br.com.asoncsts.multi.gymtrack.di

import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepository
import br.com.asoncsts.multi.gymtrack._mock.data.auth.AuthRepositoryMock
import org.koin.dsl.module

internal fun appModule() = module {
    factory<AuthRepository> {
        AuthRepositoryMock
    }
}
