package dev.skumar.letschat.core.domain.preferences

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class AppConfig(
    val apiInfo: ApiInfo
) {
    companion object {
        fun defaultInitialization() = AppConfig(
            apiInfo = ApiInfo(
                key = ""
            )
        )
    }
}


fun AppConfig.encodeToString(): String {
    return Json.encodeToString<AppConfig>(this)
}


fun String.decodeAppConfigFromString(): AppConfig {
    return Json.decodeFromString<AppConfig>(this)
}
