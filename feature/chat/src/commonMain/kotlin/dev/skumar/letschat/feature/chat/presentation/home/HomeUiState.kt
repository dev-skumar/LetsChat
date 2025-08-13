package dev.skumar.letschat.feature.chat.presentation.home


data class HomeUiState(
    val isLoading: Boolean = false,
    val isAgentRunning: Boolean = false,
    val promptField: String = "",
    val response: String = ""
)