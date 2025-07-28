package dev.skumar.letschat.app

import android.app.Application
import dev.skumar.letschat.di.initKoin
import org.koin.android.ext.koin.androidContext


class LetsChatApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@LetsChatApp)
        }
    }

}