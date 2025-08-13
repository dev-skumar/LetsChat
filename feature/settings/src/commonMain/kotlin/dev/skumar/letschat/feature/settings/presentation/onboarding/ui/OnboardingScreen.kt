package dev.skumar.letschat.feature.settings.presentation.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import dev.skumar.letschat.core.domain.preferences.AppConfig
import dev.skumar.letschat.feature.settings.presentation.onboarding.OnboardingEvent
import dev.skumar.letschat.feature.settings.presentation.onboarding.OnboardingUiState


@Composable
fun OnboardingScreen(
    uiState: OnboardingUiState,
    appConfig: AppConfig,
    processEvent: (OnboardingEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    // TODO("Implement OnboardingScreen - API key collection UI")

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
                    text = appConfig.agentConfiguration.apiKey.ifEmpty { "Api Key is not provided." },
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(

                    value = uiState.apiKeyFieldValue,
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(),
                    onValueChange = {
                        processEvent(OnboardingEvent.UpdateApiKeyFieldValue(it))
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
                        val newConfig = appConfig.copy(
                            agentConfiguration = appConfig.agentConfiguration.copy(
                                apiKey = uiState.apiKeyFieldValue
                            )
                        )
                        processEvent(OnboardingEvent.UpdateAppConfig(newConfig))
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