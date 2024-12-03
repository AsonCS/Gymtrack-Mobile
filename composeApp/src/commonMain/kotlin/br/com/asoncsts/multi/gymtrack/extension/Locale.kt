package br.com.asoncsts.multi.gymtrack.extension

import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage.EN
import br.com.asoncsts.multi.gymtrack.extension.DeviceLanguage.PT_BR

enum class DeviceLanguage {
    EN,
    PT_BR
}

val String.deviceLanguage: DeviceLanguage
    get() = when (this.lowercase()) {
        "pt",
        "pt-br" -> PT_BR

        else -> EN
    }

expect fun deviceLanguage(): DeviceLanguage
