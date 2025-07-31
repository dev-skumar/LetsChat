package dev.skumar.letschat.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dev.skumar.letschat.app.AppViewModel
import dev.skumar.letschat.core.presentation.navigation.NavigationAction
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AppNavigation(
    modifier: Modifier
) {

    val navController = rememberNavController()

    val appViewModel = koinViewModel<AppViewModel>()
    val appConfig by appViewModel.appConfig.collectAsStateWithLifecycle()

    LaunchedEffect(appViewModel.navActions) {
        appViewModel.navActions.collect { action ->
            when(action) {
                is NavigationAction.NavigateTo -> navController.navigate(action.screen)
                NavigationAction.NavigateUp -> navController.navigateUp()
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {

        NavigationGraph(
            appConfig = appConfig,
            navController = navController,
            performNavigation = appViewModel::performNavigation
        )

    }

}