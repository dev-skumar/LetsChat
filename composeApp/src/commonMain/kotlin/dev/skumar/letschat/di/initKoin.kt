package dev.skumar.letschat.di

import dev.skumar.letschat.feature.settings.di.settingsFeatureModule
import dev.skumar.letschat.service.preferences.di.preferencesServiceModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            preferencesServiceModule,
            appLevelModule,
            settingsFeatureModule
        )
    }
}