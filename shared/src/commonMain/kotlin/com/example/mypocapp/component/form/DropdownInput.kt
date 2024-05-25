package com.example.mypocapp.component.form

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState

@Composable
fun DropdownInput(
    state: AppUiState,
    onLongPress: (CommonEvent)-> Unit,
    onPress: (CommonEvent)-> Unit,
    onDismiss: (CommonEvent) -> Unit,
    onValueChanged: (CommonEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5") // implement datasource logic here
    val density = LocalDensity.current
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier
            .onSizeChanged {
                state.dropDownItemHeight = with(density) { it.height.toDp() }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            onLongPress(CommonEvent.OnDropdownLongPress)
                        },
                        onPress = {// implement different logic for short press if needed
                            onPress(CommonEvent.OnDropdownPress)
                        }
                    )
                }
                .padding(16.dp)
        ) {
            Text(text = options[state.contextMenuValue])
        }
        DropdownMenu(
            expanded = state.isContextMenuVisible,
            onDismissRequest = { onDismiss(CommonEvent.OnDropdownDismiss) },
            offset = state.contextMenuPressOffset,
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        onValueChanged(
                            CommonEvent.OnDropdownValueChanged(
                                options.indexOf(selectionOption)
                            )
                        )
                    }
                )
            }
        }
    }
}