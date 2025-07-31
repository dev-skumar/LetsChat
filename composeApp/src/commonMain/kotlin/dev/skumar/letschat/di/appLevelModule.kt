package dev.skumar.letschat.di

import dev.skumar.letschat.app.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appLevelModule = module {

    viewModel {
        AppViewModel(get())
    }

}