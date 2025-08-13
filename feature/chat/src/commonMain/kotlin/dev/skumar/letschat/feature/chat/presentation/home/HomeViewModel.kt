package dev.skumar.letschat.feature.chat.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.skumar.letschat.core.domain.agent.AgentConfiguration
import dev.skumar.letschat.core.domain.error.ErrorController
import dev.skumar.letschat.core.domain.preferences.PreferencesRepository
import dev.skumar.letschat.core.domain.utils.Result
import dev.skumar.letschat.feature.chat.domain.usecases.ChatUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val errorController: ErrorController,
    private val preferencesRepository: PreferencesRepository,
    private val useCases: ChatUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun processEvent(event: HomeEvent) {
        viewModelScope.launch { 
            when(event) {

                HomeEvent.SendTextInput -> {
                    sendTextInput()
                }

                is HomeEvent.UpdatePromptField -> {
                    _uiState.update { it.copy(promptField = event.newValue) }
                }

            }
        }
    }



    fun initializeAgent(config: AgentConfiguration) {
        viewModelScope.launch {
            useCases.initializeAgent(config).onEach { result ->
                when(result) {

                    Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        _uiState.update { it.copy(isLoading = false) }
                    }

                    is Result.Failure -> {
                        val errorDialog = result.error.getErrorDialog(
                            enableRetry = true,
                            onRetry = {
                                viewModelScope.launch { initializeAgent(config) }
                                errorController.closeError()
                            },
                            enableOkay = false,
                            onOkay = { }
                        )
                        errorController.displayError(errorDialog)
                    }
                }
            }.collect()
        }
    }



    private suspend fun sendTextInput() {
        useCases.processTextInput(uiState.value.promptField).onEach { result ->
            when(result) {

                Result.Loading -> {
                    _uiState.update { it.copy(isAgentRunning = true) }
                }

                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            isAgentRunning = false,
                            response = result.data,
                            promptField = ""
                        )
                    }
                }

                is Result.Failure -> {
                    val errorDialog = result.error.getErrorDialog {
                        viewModelScope.launch {
                            _uiState.update { it.copy(isAgentRunning = false) }
                            errorController.closeError()
                        }
                    }
                    errorController.displayError(errorDialog)
                }

            }
        }.collect()
    }



    override fun onCleared() {
        viewModelScope.launch { useCases.closeAgent() }
        super.onCleared()
    }

}