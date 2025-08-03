package dev.skumar.letschat.feature.chat.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.skumar.letschat.core.domain.error.ErrorController
import dev.skumar.letschat.core.domain.preferences.PreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val errorController: ErrorController,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun processEvent(event: HomeEvent) {
        viewModelScope.launch { 
//            when(event) {
//
//            }
        }
    }

}