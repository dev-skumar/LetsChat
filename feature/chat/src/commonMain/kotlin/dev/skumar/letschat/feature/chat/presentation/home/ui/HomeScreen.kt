package dev.skumar.letschat.feature.chat.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Loading...",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        }

        false -> {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f)
                ) {
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

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                ) {
                    Text(
                        text = uiState.response.ifEmpty { "(^-^) Waiting for your input (^-^)" },
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.3f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        OutlinedTextField(

                            value = uiState.promptField,
                            textStyle = LocalTextStyle.current.copy(),
                            onValueChange = {
                                processEvent(HomeEvent.UpdatePromptField(it))
                            },

                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),

                            modifier = Modifier
                                .fillMaxWidth()

                        )

                        Button(
                            onClick = {
                                processEvent(HomeEvent.SendTextInput)
                            }
                        ) {
                            Text(
                                text = "Send"
                            )
                        }

                    }
                }
            }

        }
    }
}