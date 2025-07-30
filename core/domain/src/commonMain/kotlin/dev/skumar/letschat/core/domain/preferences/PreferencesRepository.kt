package dev.skumar.letschat.core.domain.preferences

import kotlinx.coroutines.flow.Flow


interface PreferencesRepository {

    fun getAppConfig(): Flow<AppConfig?>

    suspend fun updateAppConfig(config: AppConfig)

}