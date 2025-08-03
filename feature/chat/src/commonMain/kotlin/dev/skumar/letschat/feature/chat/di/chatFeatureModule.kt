package dev.skumar.letschat.feature.chat.di

import dev.skumar.letschat.feature.chat.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val chatFeatureModule = module {

    viewModel {
        HomeViewModel(get(), get())
    }

}