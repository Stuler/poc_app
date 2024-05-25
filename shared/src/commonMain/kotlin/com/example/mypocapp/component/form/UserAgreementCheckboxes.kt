package com.example.mypocapp.component.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState

@Composable
fun UserAgreementCheckboxes(
    state: AppUiState,
    onClickTriState: (CommonEvent) -> Unit,
    onClickCheckbox: (CommonEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onClickTriState(CommonEvent.OnClickTriState)
            }
            .padding(end = 16.dp)
    ) {
        TriStateCheckbox(
            state = state.triState,
            onClick = {
                onClickTriState(CommonEvent.OnClickTriState)
            }
        )
        Text(text = "Agreements")
    }
    state.userAgreementItems.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 32.dp)
                .clickable {
                    onClickCheckbox(CommonEvent.OnClickCheckbox(index))
                }
                .padding(end = 16.dp)
        ) {
            Checkbox(
                checked = info.isChecked,
                onCheckedChange = {
                    onClickCheckbox(CommonEvent.OnClickCheckbox(index))
                }
            )
            Text(text = info.text)
        }
    }
}