package com.example.mypocapp.component

import androidx.compose.runtime.Composable
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState
import com.example.mypocapp.model.viewModel.GlobalViewModel

@Composable
actual fun NavDrawer(
    state: AppUiState,
    onEvent: (CommonEvent) -> Unit,
    viewModel: GlobalViewModel
) {
}