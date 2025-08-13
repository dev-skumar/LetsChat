package dev.skumar.letschat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.skumar.letschat.core.domain.preferences.AppConfig
import dev.skumar.letschat.core.presentation.navigation.NavigationAction
import dev.skumar.letschat.core.presentation.navigation.Screen
import dev.skumar.letschat.feature.chat.presentation.home.HomeUiData
import dev.skumar.letschat.feature.chat.presentation.home.HomeViewModel
import dev.skumar.letschat.feature.chat.presentation.home.ui.HomeScreen
import dev.skumar.letschat.feature.settings.presentation.onboarding.OnboardingViewModel
import dev.skumar.letschat.feature.settings.presentation.onboarding.ui.OnboardingScreen
import dev.skumar.letschat.feature.settings.presentation.settings.SettingsViewModel
import dev.skumar.letschat.feature.settings.presentation.settings.ui.SettingsScreen
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun NavigationGraph(
    appConfig: AppConfig?,
    navController: NavHostController,
    performNavigation: (NavigationAction) -> Unit
) {

    when(appConfig) {

        null -> {
            Text("Loading...")
        }

        else -> {

            NavHost(
                navController = navController,
                startDestination = if (appConfig.agentConfiguration.apiKey.isEmpty()) Screen.Onboarding else Screen.Home
            ) {

                composable<Screen.Onboarding> {

                    val onboardingVM = koinViewModel<OnboardingViewModel>()
                    val uiState by onboardingVM.uiState.collectAsStateWithLifecycle()

                    OnboardingScreen(
                        uiState = uiState,
                        appConfig = appConfig,
                        processEvent = onboardingVM::processEvent
                    )

                }


                composable<Screen.Home> {

                    val homeVM = koinViewModel<HomeViewModel>()
                    val uiState by homeVM.uiState.collectAsStateWithLifecycle()

                    HomeScreen(
                        uiState = uiState,
                        uiData = HomeUiData(),
                        performNavigation = performNavigation,
                        processEvent = homeVM::processEvent
                    )

                }


                composable<Screen.Settings> {

                    val settingsVM = koinViewModel<SettingsViewModel>()
                    val uiState by settingsVM.uiState.collectAsStateWithLifecycle()

                    SettingsScreen(
                        uiState = uiState,
                        appConfig = appConfig,
                        performNavigation = performNavigation,
                        processEvent = settingsVM::processEvent
                    )

                }

            }
        }
    }
}