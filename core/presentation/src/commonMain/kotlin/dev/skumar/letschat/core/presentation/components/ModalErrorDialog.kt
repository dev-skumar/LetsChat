package dev.skumar.letschat.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.skumar.letschat.core.domain.error.ErrorDialog
import dev.skumar.letschat.core.domain.utils.prettyPrint


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalErrorDialog(
    error: ErrorDialog,
    modifier: Modifier = Modifier
) {

    // TODO("Implement Error Dialog UI")

    BasicAlertDialog(
        onDismissRequest = {},
        modifier = Modifier
            .fillMaxWidth(0.85f),
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = error.prettyPrint(),
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (error.enableRetry) {

                    Button(
                        onClick = error.onRetry
                    ) {
                        Text(
                            text = "Retry"
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                }

                Button(
                    onClick = error.onOkay
                ) {
                    Text(
                        text = "Okay"
                    )
                }
            }

        }
    }

}