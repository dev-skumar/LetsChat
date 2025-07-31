package dev.skumar.letschat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.skumar.letschat.core.domain.preferences.AppConfig
import dev.skumar.letschat.core.presentation.navigation.NavigationAction
import dev.skumar.letschat.core.presentation.navigation.Screen


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
                startDestination = if (appConfig.apiInfo.key.isEmpty()) Screen.ApiKey else Screen.Home
            ) {

                composable<Screen.ApiKey> {

                }


                composable<Screen.Home> {

                }


                composable<Screen.Settings> {

                }

            }
        }
    }
}