package com.example.mypocapp.component.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState

@Composable
fun UserTypeRadioButtons(
    state: AppUiState,
    onEvent: (CommonEvent) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        state.userRightsItems.forEachIndexed { index, info ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = info.isChecked,
                        onClick = {
                            onEvent(CommonEvent.OnRadioButtonClick(index))
                        }
                    )
            ) {
                RadioButton(
                    selected = info.isChecked,
                    onClick = {
                        onEvent(CommonEvent.OnRadioButtonClick(index))
                    }
                )
                Text(text = info.text)
            }
        }
    }
}