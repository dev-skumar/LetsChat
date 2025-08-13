package dev.skumar.letschat.feature.chat.di

import dev.skumar.letschat.feature.chat.domain.usecases.ChatUseCases
import dev.skumar.letschat.feature.chat.domain.usecases.CloseAgent
import dev.skumar.letschat.feature.chat.domain.usecases.InitializeAgent
import dev.skumar.letschat.feature.chat.domain.usecases.ProcessTextInput
import dev.skumar.letschat.feature.chat.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val chatFeatureModule = module {

    single<ChatUseCases> {
        ChatUseCases(
            initializeAgent = InitializeAgent(get()),
            closeAgent = CloseAgent(get()),
            processTextInput = ProcessTextInput(get())
        )
    }

    viewModel {
        HomeViewModel(get(), get(), get())
    }

}