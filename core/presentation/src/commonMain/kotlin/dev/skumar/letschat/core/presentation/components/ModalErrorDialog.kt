package dev.skumar.letschat.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dev.skumar.letschat.core.domain.error.ErrorDialog


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
            Text(
                text = error.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

}