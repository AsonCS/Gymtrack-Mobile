package br.com.asoncsts.multi.gymtrack.di

import android.content.Context
import br.com.asoncsts.multi.gymtrack.data.auth.AndroidAuthRepository
import br.com.asoncsts.multi.gymtrack.data.auth.AuthRepository
import org.koin.dsl.module

fun androidModule(
    context: Context
) = module {
    factory { context }

    single<AuthRepository> {
        AndroidAuthRepository(
            context
        )
        // AuthRepositoryMock
    }
}
