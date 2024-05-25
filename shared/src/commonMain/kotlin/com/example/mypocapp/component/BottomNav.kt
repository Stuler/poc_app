package com.example.mypocapp.component

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState
import kotlinx.coroutines.CoroutineScope

@Composable
expect fun BottomNav(
    state: AppUiState,
    onEvent: (CommonEvent) -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope,
)