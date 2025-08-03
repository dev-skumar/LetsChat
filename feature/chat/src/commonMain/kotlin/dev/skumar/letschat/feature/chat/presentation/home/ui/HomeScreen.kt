package dev.skumar.letschat.feature.chat.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dev.skumar.letschat.core.presentation.navigation.NavigationAction
import dev.skumar.letschat.core.presentation.navigation.Screen
import dev.skumar.letschat.feature.chat.presentation.home.HomeEvent
import dev.skumar.letschat.feature.chat.presentation.home.HomeUiData
import dev.skumar.letschat.feature.chat.presentation.home.HomeUiState


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    uiData: HomeUiData,
    performNavigation: (NavigationAction) -> Unit,
    processEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO("Implement UI")

    when(uiState.isLoading) {

        true -> {
            Text(
                text = "Loading...",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        false -> {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Text(
                    text = "This is Temporary Home Screen",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Button(
                    onClick = {
                        performNavigation(NavigationAction.NavigateTo(Screen.Settings))
                    }
                ) {
                    Text(
                        text = "Settings"
                    )
                }
            }

        }
    }
}