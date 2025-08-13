package dev.skumar.letschat.service.agent.di

import dev.skumar.letschat.core.domain.agent.AgentController
import dev.skumar.letschat.service.agent.AgentControllerImpl
import org.koin.dsl.module


val agentServiceModule = module {

    single<AgentController> {
        AgentControllerImpl()
    }

}