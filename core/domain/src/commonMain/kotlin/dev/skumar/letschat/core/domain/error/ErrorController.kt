package dev.skumar.letschat.core.domain.error

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ErrorController {

    private val _errorDialog: MutableStateFlow<ErrorDialog?> = MutableStateFlow(null)
    val errorDialog: StateFlow<ErrorDialog?> = _errorDialog.asStateFlow()


    fun displayError(error: ErrorDialog) {
        _errorDialog.update { error }
    }


    fun closeError() {
        _errorDialog.update { null }
    }

}