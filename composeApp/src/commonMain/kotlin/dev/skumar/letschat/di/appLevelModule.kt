package dev.skumar.letschat.di

import dev.skumar.letschat.app.AppViewModel
import dev.skumar.letschat.core.domain.error.ErrorController
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appLevelModule = module {

    single {
        ErrorController()
    }

    viewModel {
        AppViewModel(get(), get())
    }

}