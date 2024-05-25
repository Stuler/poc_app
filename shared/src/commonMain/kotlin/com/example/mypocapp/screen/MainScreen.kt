package com.example.mypocapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.component.BottomNav
import com.example.mypocapp.component.TopBar
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState

@Composable
fun MainScreen(
    state: AppUiState,
    onEvent: (CommonEvent) -> Unit,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNav(
                state = state,
                onEvent = onEvent,
                drawerState = drawerState,
                scope = scope
            )
        }
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {

            }
        }
    }

    FormScreen(
        state   = state,
        isOpen = state.isFormScreenOpen,
        onEvent = onEvent
    )

    ApiScreen(
        state   = state,
        isOpen = state.isApiScreenOpen,
        onEvent = onEvent
    )

    ListScreen(
        state = state,
        isOpen = state.isListScreenOpen,
        onEvent = onEvent
    )
}