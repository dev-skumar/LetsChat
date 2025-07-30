package dev.skumar.letschat.service.preferences.repository

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.toFlowSettings
import dev.skumar.letschat.core.domain.preferences.AppConfig
import dev.skumar.letschat.core.domain.preferences.PreferencesRepository
import dev.skumar.letschat.core.domain.preferences.decodeAppConfigFromString
import dev.skumar.letschat.core.domain.preferences.encodeToString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl: PreferencesRepository {

    private val settings = Settings()
    private val observableSettings: ObservableSettings = settings as ObservableSettings

    @OptIn(ExperimentalSettingsApi::class)
    private val flowSettings = observableSettings.toFlowSettings()


    init {
        if (!settings.hasKey(KEY_APP_CONFIG)) {
            settings.putString(KEY_APP_CONFIG, AppConfig.Companion.defaultInitialization().encodeToString())
        }
    }


    @OptIn(ExperimentalSettingsApi::class)
    override fun getAppConfig(): Flow<AppConfig?> {
        return flowSettings
            .getStringOrNullFlow(KEY_APP_CONFIG)
            .map {
                it?.decodeAppConfigFromString()
            }
    }


    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun updateAppConfig(config: AppConfig) {
        flowSettings.putString(KEY_APP_CONFIG, config.encodeToString())
    }


    companion object {
        private const val KEY_APP_CONFIG = "app_config"
    }

}