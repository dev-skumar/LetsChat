package dev.skumar.letschat.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.skumar.letschat.core.domain.error.ErrorController
import dev.skumar.letschat.core.domain.error.ErrorDialog
import dev.skumar.letschat.core.domain.preferences.PreferencesRepository
import dev.skumar.letschat.core.presentation.navigation.NavigationAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class AppViewModel(
    private val preferencesRepository: PreferencesRepository,
    private val errorController: ErrorController
) : ViewModel() {

    val appConfig = preferencesRepository
        .getAppConfig()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )


    val errorDialog: StateFlow<ErrorDialog?> = errorController.errorDialog


    // ===============================[ Navigation Logic ]================================

    private val _navActions = Channel<NavigationAction>(Channel.UNLIMITED)
    val navActions = _navActions.receiveAsFlow()

    fun performNavigation(action: NavigationAction) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _navActions.send(action)
        }
    }

    // ===================================================================================

}