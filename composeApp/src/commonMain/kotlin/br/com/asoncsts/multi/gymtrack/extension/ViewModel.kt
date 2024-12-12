package br.com.asoncsts.multi.gymtrack.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

inline fun <T : ViewModel> T.launch(
    crossinline block: suspend T.() -> Unit
) {
    viewModelScope.launch {
        block()
    }
}
