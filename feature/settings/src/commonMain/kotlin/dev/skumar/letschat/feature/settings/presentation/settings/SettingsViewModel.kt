package dev.skumar.letschat.feature.settings.presentation.settings



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.skumar.letschat.core.domain.error.ErrorController
import dev.skumar.letschat.core.domain.preferences.AppConfig
import dev.skumar.letschat.core.domain.utils.Result
import dev.skumar.letschat.feature.settings.domain.usecases.SettingsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val errorController: ErrorController,
    private val useCases: SettingsUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()


    fun processEvent(event: SettingsEvent) {
        viewModelScope.launch {
            when (event) {

                is SettingsEvent.UpdateApiKeyField -> {
                    _uiState.update { it.copy(apiKeyField = event.newValue) }
                }

                is SettingsEvent.UpdateSystemPromptField -> {
                    _uiState.update { it.copy(systemPromptField = event.newValue) }
                }

                is SettingsEvent.UpdateAppConfig -> {
                    updateAppConfig(event.config)
                }
            }
        }
    }


    private suspend fun updateAppConfig(config: AppConfig) {
        useCases.updateAppConfig(config).onEach { result ->
            when(result) {

                Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            apiKeyField = "",
                            systemPromptField = "",
                            isLoading = false
                        )
                    }
                }

                is Result.Failure -> {
                    val errorDialog = result.error.getErrorDialog(
                        onOkay = {
                            viewModelScope.launch {
                                errorController.closeError()
                                _uiState.update { it.copy(isLoading = false) }
                            }
                        }
                    )
                    errorController.displayError(errorDialog)
                }

            }
        }.collect()
    }

}