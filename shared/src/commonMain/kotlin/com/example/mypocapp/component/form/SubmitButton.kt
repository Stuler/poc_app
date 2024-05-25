package com.example.mypocapp.component.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent

@Composable
fun SubmitButton(
    onEvent: (CommonEvent) -> Unit
) {
    Button(
        onClick = { onEvent(CommonEvent.OnSubmit) },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Text("Submit")
    }
}