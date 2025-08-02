package dev.skumar.letschat.feature.settings.di

import dev.skumar.letschat.feature.settings.domain.usecases.SettingsUseCases
import dev.skumar.letschat.feature.settings.domain.usecases.UpdateAppConfig
import dev.skumar.letschat.feature.settings.presentation.onboarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val settingsFeatureModule = module {

    single {
        SettingsUseCases(
            updateAppConfig = UpdateAppConfig(get())
        )
    }


    viewModel {
        OnboardingViewModel(get(), get())
    }

}