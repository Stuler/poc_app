package com.example.mypocapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.component.BottomSheet
import com.example.mypocapp.component.form.DropdownInput
import com.example.mypocapp.component.form.EmailInput
import com.example.mypocapp.component.form.PasswordInput
import com.example.mypocapp.component.form.SubmitButton
import com.example.mypocapp.component.form.SwitchRememberPassword
import com.example.mypocapp.component.form.UserAgreementCheckboxes
import com.example.mypocapp.component.form.UserTypeRadioButtons
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState

@Composable
fun FormScreen(
    state: AppUiState,
    isOpen: Boolean,
    onEvent: (CommonEvent) -> Unit,
    modifier: Modifier = Modifier
){
    BottomSheet(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Form Screen",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )

                EmailInput(
                    value = state.email,
                    onEvent = onEvent
                )

                PasswordInput(
                    value = state.password,
                    onEvent = onEvent
                )

                SwitchRememberPassword(
                    state = state,
                    onCheckedChange = {
                        onEvent(CommonEvent.OnSwitchChanged)
                    }
                )

                DropdownInput(
                    state = state,
                    onDismiss = onEvent,
                    onLongPress = onEvent,
                    onPress = onEvent,
                    onValueChanged = onEvent
                )

                UserTypeRadioButtons(
                    state = state,
                    onEvent = onEvent
                )

                UserAgreementCheckboxes(
                    state = state,
                    onClickTriState = onEvent,
                    onClickCheckbox = onEvent
                )

                SubmitButton(
                    onEvent = onEvent
                )

            }
            IconButton(
                onClick = {
                    onEvent(CommonEvent.DismissScreen)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}