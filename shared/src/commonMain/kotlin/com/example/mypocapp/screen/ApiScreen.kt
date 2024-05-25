package com.example.mypocapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.component.BottomSheet
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState

@Composable
fun ApiScreen(
    state: AppUiState,
    isOpen: Boolean,
    onEvent: (CommonEvent) -> Unit
){
    val apiLink = "https://is.tradix.cz/connector/api.php?token=qaxYugEUBUbviu2EUyfN5RNcXvTfWSYK&app=kotlin"
    val coroutineScope = rememberCoroutineScope()

    BottomSheet(
        visible = isOpen,
        modifier = Modifier.fillMaxWidth()
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Api Screen",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                    )

                Spacer(modifier = Modifier.height(16.dp))

                IconButton(
                    onClick = {
                        onEvent(CommonEvent.GetApiResult(apiLink, coroutineScope))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "Connect to API"
                    )
                }
                Text(text = state.apiConnectionMessage)
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

// call getData() from shared/src/commonMain/kotlin/GlobalViewModel.kt
// shows if connection and data obtained from API is successful
