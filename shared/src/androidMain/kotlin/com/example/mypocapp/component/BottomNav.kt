package com.example.mypocapp.component

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    NavigationBar {
        state.menuItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = item.title == state.selectedMenuItem,
                onClick = {
                    onEvent(CommonEvent.BottomBarButtonClicked(item.title))
                    // retain selected screen when menu is clicked
                    if (item.title == "Menu") {
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
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = if (index == item.index) {
                                item.selectedIcon
                            } else
                                item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}