package dev.skumar.letschat.feature.settings.presentation.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.skumar.letschat.core.domain.preferences.AppConfig
import dev.skumar.letschat.core.domain.utils.prettyPrint
import dev.skumar.letschat.core.presentation.navigation.NavigationAction
import dev.skumar.letschat.feature.settings.presentation.settings.SettingsEvent
import dev.skumar.letschat.feature.settings.presentation.settings.SettingsUiState


@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    appConfig: AppConfig,
    performNavigation: (NavigationAction) -> Unit,
    processEvent: (SettingsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO("Implement Sleek Settings UI")

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
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = uiState.prettyPrint(),
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = appConfig.prettyPrint(),
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(

                    value = uiState.apiKeyField,
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(),
                    onValueChange = {
                        processEvent(SettingsEvent.UpdateApiKeyField(it))
                    },

                    label = {
                        Text(
                            text = "Enter API Key:",
                            fontStyle = FontStyle.Italic,
                            color = Color.LightGray
                        )
                    },

                    supportingText = {
                        Text(text = "for Gemini 2.0 Flash Lite Model")
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )

                )

                Button(
                    onClick = {
                        processEvent(SettingsEvent.UpdateAppConfig(
                            appConfig.copy(
                                apiInfo = appConfig.apiInfo.copy(
                                    key = uiState.apiKeyField
                                )
                            )
                        ))
                    }
                ) {
                    Text(
                        text = "Submit"
                    )
                }


            }
        }
    }

}