package br.com.asoncsts.multi.gymtrack.extension

import br.com.asoncsts.multi.gymtrack.di.json
import io.ktor.util.date.getTimeMillis
import kotlinx.serialization.encodeToString

fun getTimeSeconds() = getTimeMillis() / 1_000

fun Any.prettyPrint() = json.encodeToString(this)
