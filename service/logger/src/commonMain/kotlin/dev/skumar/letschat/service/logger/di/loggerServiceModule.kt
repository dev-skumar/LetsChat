package dev.skumar.letschat.service.logger.di

import co.touchlab.kermit.DefaultFormatter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter
import dev.skumar.letschat.core.domain.logger.LoggingService
import dev.skumar.letschat.service.logger.LoggingServiceImpl
import org.koin.dsl.module


val loggingServiceModule = module {

    single<Logger> {
        Logger(
            config = loggerConfigInit(platformLogWriter(DefaultFormatter))
        )
    }

    single<LoggingService> {
        LoggingServiceImpl(get())
    }

}