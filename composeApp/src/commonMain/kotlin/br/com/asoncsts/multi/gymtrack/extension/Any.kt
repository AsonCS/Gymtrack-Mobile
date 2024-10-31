package br.com.asoncsts.multi.gymtrack.extension

import io.ktor.util.date.getTimeMillis

fun getTimeSeconds() = getTimeMillis() / 1_000
