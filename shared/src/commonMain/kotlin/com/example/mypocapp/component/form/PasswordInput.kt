package com.example.mypocapp.component.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent

@Composable
fun PasswordInput(
    value: String,
    onEvent: (CommonEvent) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onEvent(CommonEvent.OnPasswordChanged(it)) },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        label = { Text(text = "Password") },
        placeholder = { Text(text = "12334444") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}