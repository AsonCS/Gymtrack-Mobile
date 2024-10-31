package br.com.asoncsts.multi.gymtrack.di

import org.koin.core.module.Module
import org.koin.dsl.koinApplication

fun koinApplication(
    platformModule: Module
) = koinApplication {
    modules(
        platformModule,
        appModule(),
        uiModule()
    )
}
