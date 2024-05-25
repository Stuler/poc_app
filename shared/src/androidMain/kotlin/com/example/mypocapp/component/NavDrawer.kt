package com.example.mypocapp.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.state.AppUiState
import com.example.mypocapp.model.type.NavigationItem
import com.example.mypocapp.model.viewModel.GlobalViewModel
import com.example.mypocapp.screen.MainScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
actual fun NavDrawer(
    state: AppUiState,
    onEvent: (CommonEvent) -> Unit,
    viewModel: GlobalViewModel
) {
    val items = listOf(
        NavigationItem(
            index = 0,
            title = "Tasks",
            selectedIcon = Icons.Filled.CheckCircle,
            unselectedIcon = Icons.Outlined.CheckCircle,
        ),
        NavigationItem(
            index = 1,
            title = "Users",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
        ),
        NavigationItem(
            index = 2,
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val scope = rememberCoroutineScope()
        val isDrawerOpen = state.isDrawerOpen
        val drawerState = rememberDrawerState(
            initialValue =
            if (isDrawerOpen) DrawerValue.Open
            else DrawerValue.Closed
        )
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        ModalNavigationDrawer(
            drawerContent = {
                Spacer(modifier = Modifier.height(16.dp))
                ModalDrawerSheet {
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                //viewModel.selectMenuItem(item.title)
                                selectedItemIndex = index
                                clickDrawerMenu(scope, item.title, drawerState)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            },
            drawerState = drawerState,
        ) {
            MainScreen(
                state = state,
                onEvent = viewModel::onEvent,
                drawerState = drawerState
            )
        }
    }
}


/**
 * Handle navigation when drawer menu is clicked
 *  Todo: implement navigation - probably wil need to move to globalViewModel with iOS app
 */
private fun clickDrawerMenu(
    scope: CoroutineScope,
    title: String,
    drawerState: DrawerState
) {
    when (title) {
        "Tasks" -> {
            scope.launch {
                drawerState.close()
            }
        }
        "Users" -> {
            scope.launch {
                drawerState.close()
            }
        }
        "Settings" -> {
            scope.launch {
                drawerState.close()
            }
        }
    }
}