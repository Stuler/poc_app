package com.example.mypocapp.component.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent

@Composable
fun EmailInput(
    value: String,
    onEvent: (CommonEvent) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onEvent(CommonEvent.OnEmailChanged(it)) },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        label = { Text("Email") },
        placeholder = { Text("type your email") },
    )
}