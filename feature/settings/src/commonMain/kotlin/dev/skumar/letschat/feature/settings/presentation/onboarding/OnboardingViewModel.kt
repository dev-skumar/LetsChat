package dev.skumar.letschat.feature.settings.presentation.onboarding


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


class OnboardingViewModel(
    private val errorController: ErrorController,
    private val useCases: SettingsUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()


    fun processEvent(event: OnboardingEvent) {
        viewModelScope.launch {
            when(event) {

                is OnboardingEvent.UpdateApiKeyFieldValue -> {
                    _uiState.update { it.copy(apiKeyFieldValue = event.newValue) }
                }

                is OnboardingEvent.UpdateAppConfig -> {
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
                            apiKeyFieldValue = "",
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