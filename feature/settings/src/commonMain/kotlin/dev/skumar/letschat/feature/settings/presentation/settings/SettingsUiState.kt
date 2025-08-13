package dev.skumar.letschat.feature.settings.presentation.settings


data class SettingsUiState(
    val isLoading: Boolean = false,
    val apiKeyField: String = "",
    val systemPromptField: String = ""
)