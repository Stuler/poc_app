package com.example.mypocapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState

@Composable
fun ListScreen(
    state: AppUiState,
    isOpen: Boolean,
    onEvent: (CommonEvent) -> Unit
) {
    val userDataList = state.userData
    val apiMessage = state.apiConnectionMessage

    BottomSheet(
        visible = isOpen,
        modifier = Modifier.fillMaxWidth()
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        )
        {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "List Screen",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (userDataList != null) {
                    if (userDataList.isEmpty()) {
                        // UserData je prázdné, zobrazit chybovou zprávu
                        Text(text = "Nebylo provedeno připojení k API; data nebyla načtena.")
                    } else {
                        // UserData obsahuje data, vypsat je
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Vypsat data
                            userDataList.forEach { user ->
                                Text(text = "ID: ${user.id}")
                                Text(text = "Name: ${user.name}")
                                Text(text = "Email: ${user.email}")
                                Text(text = "Date: ${user.date}")

                                // Vypsat tlačítka
                                user.buttons.forEachIndexed { index, button ->
                                    Text(text = "Button $index: $button")
                                }

                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                } else {
                    // UserData je null, zobrazit chybovou zprávu
                    Text(text = apiMessage)
                }
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