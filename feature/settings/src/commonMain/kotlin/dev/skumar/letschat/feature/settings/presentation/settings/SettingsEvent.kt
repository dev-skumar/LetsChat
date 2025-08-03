package dev.skumar.letschat.feature.settings.presentation.settings

import dev.skumar.letschat.core.domain.preferences.AppConfig


sealed class SettingsEvent {

    data class UpdateApiKeyField(val newValue: String): SettingsEvent()

    data class UpdateAppConfig(val config: AppConfig): SettingsEvent()

}