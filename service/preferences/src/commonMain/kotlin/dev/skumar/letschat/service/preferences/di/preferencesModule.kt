package dev.skumar.letschat.service.preferences.di

import dev.skumar.letschat.core.domain.preferences.PreferencesRepository
import dev.skumar.letschat.service.preferences.repository.PreferencesRepositoryImpl
import org.koin.dsl.module


val preferencesModule = module {

    single<PreferencesRepository> {
        PreferencesRepositoryImpl()
    }

}