package br.com.asoncsts.multi.gymtrack.extension

import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage.*

enum class DeviceLanguage(val value: String) {
    En("en"),
    Es("es"),
    Pt("pt")
}

val String.deviceLanguage: DeviceLanguage
    get() = when (this.lowercase()) {
        "es" -> Es
        "pt",
        "pt-br" -> Pt

        else -> En
    }

expect fun deviceLanguage(): DeviceLanguage
