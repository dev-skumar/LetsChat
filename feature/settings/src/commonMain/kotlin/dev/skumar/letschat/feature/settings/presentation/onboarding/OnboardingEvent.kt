package dev.skumar.letschat.feature.settings.presentation.onboarding

import dev.skumar.letschat.core.domain.preferences.AppConfig


sealed class OnboardingEvent {

    data class UpdateApiKeyFieldValue(val newValue: String): OnboardingEvent()

    data class UpdateAppConfig(val config: AppConfig): OnboardingEvent()

}