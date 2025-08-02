package dev.skumar.letschat.feature.settings.presentation.onboarding


data class OnboardingUiState(
    val isLoading: Boolean = false,
    val apiKeyFieldValue: String = ""
)