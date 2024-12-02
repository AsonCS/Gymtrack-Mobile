package br.com.asoncsts.multi.gymtrack.di

import io.ktor.client.engine.apache5.Apache5

actual val platform = object : Platform {
    override val engine = Apache5
}
