package com.example.mypocapp.model.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import com.example.mypocapp.model.type.NavigationItem
import com.example.mypocapp.model.type.ToggleableInfo
import com.example.mypocapp.model.type.UserData

/**
 * Type that represents the state of the app.
 * Todo: refactor into multiple classes for better readability and maintainability
 * Todo: divide into ScreenState, ApiState, FormState, ListState, NavigationState, etc.
 */
data class AppUiState(
    val userData: List<UserData>? = emptyList(),
    val selectedMenuItem: String? = null,
    val isFormScreenOpen: Boolean = false,
    val isApiScreenOpen: Boolean = false,
    val isListScreenOpen: Boolean = false,
    val isMenuScreenOpen: Boolean = false,
    val isDrawerOpen: Boolean = false,
    val isMenuButtonSelected: Boolean = false,
    val isFormButtonSelected: Boolean = false,
    val isApiButtonSelected: Boolean = false,
    val isListButtonSelected: Boolean = false,
    val apiConnectionMessage: String = "",
    val actualScreen: String = "",
    val isContextMenuVisible: Boolean = false,
    val contextMenuPressOffset: DpOffset = DpOffset.Zero,
    val isSwitchChecked: Boolean = false,
    val email: String = "",
    val password: String = "",
    var dropDownItemHeight: Dp = Dp.Unspecified,
    val contextMenuValue: Int = 0,
    val selectedRadioButtonItemIndex: Int = 0,
    val triState: ToggleableState = ToggleableState.Off,
    val userRightsItems: Array<ToggleableInfo> =
        arrayOf(
            ToggleableInfo(
                isChecked = false,
                text = "Admin"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "User"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Employee"
            )
        ),
    val userAgreementItems: Array<ToggleableInfo> =
        arrayOf(
            ToggleableInfo(
                isChecked = false,
                text = "Customer Agreement"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "GDPR Agreement"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Terms of Service"
            ),
        ),
    val menuItems: Array<NavigationItem> =
        arrayOf(
            NavigationItem(
                index = 0,
                title = "Menu",
                selectedIcon = Icons.Filled.Menu,
                unselectedIcon = Icons.Filled.Menu
            ),
            NavigationItem(
                index = 1,
                title = "Form",
                selectedIcon = Icons.Filled.Edit,
                unselectedIcon = Icons.Filled.Edit
            ),
            NavigationItem(
                index = 2,
                title = "Api",
                selectedIcon = Icons.Filled.PlayArrow,
                unselectedIcon = Icons.Filled.PlayArrow
            ),
            NavigationItem(
                index = 3,
                title = "List",
                selectedIcon = Icons.Filled.List,
                unselectedIcon = Icons.Filled.List
            )
        ),
){

}