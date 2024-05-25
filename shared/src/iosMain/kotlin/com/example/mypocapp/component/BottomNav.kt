package com.example.mypocapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
actual fun BottomNav(
    state: AppUiState,
    onEvent: (CommonEvent) -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope,
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        for (menuItem in state.menuItems) {
            Button(
                onClick = {
                    onEvent(CommonEvent.BottomBarButtonClicked(menuItem.title))
                    if (menuItem.title == "Menu") {
                        if (drawerState.isClosed) {
                            scope.launch {
                                drawerState.open()
                            }
                        } else {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    }
                },
                shape = AbsoluteCutCornerShape(0.dp),
                modifier = Modifier
                    .aspectRatio(1.0f)
                    .fillMaxSize()
                    .weight(1.0f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    focusedElevation = 0.dp,
                )
            ) {
                Text(text = menuItem.title)
            }
        }
    }


}