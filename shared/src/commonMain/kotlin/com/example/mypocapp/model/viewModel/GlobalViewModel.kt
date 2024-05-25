package com.example.mypocapp.model.viewModel

import com.example.mypocapp.model.event.CommonEvent
import com.example.mypocapp.model.service.HttpService
import com.example.mypocapp.model.state.AppUiState
import com.example.mypocapp.model.type.ApiResult
import com.example.mypocapp.model.type.ERoute
import com.example.mypocapp.model.type.UserData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * The state of the app.
 * Contains the data of the user, the selected menu item and the state of the screens.
 * TODO: refactor and dispatch to standalone view models for screens, forms, etc.
 */

class GlobalViewModel(private val httpService: HttpService): ViewModel() {
    // makes it possible to update the state - similar to @Observable in react.js
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow() // makes it possible to read the state

    /**
     * Handles the events from the screens.
     * TODO: refactor to dispatch requests to standalone methods for screens, forms, etc.
     */
    fun onEvent(event: CommonEvent) {
        println("actual screen: " + _uiState.value.actualScreen)
        println("selected menu item: " + _uiState.value.selectedMenuItem)
        when(event){
            is CommonEvent.DismissScreen -> {
                closeAllScreens()
            }

            is CommonEvent.GetApiResult-> {
                getApiResult(event)
            }

            is CommonEvent.BottomBarButtonClicked -> {
                handleSelectBottomMenuItem(event.menuItem)
            }

            is CommonEvent.OnEmailChanged -> {
                updateEmail(event.value)
            }

            is CommonEvent.OnPasswordChanged -> {
                updatePassword(event.value)
            }

            is CommonEvent.OnSwitchChanged -> {
                updateRememberPasswordSwitch()
            }

            is CommonEvent.OnDropdownLongPress -> {
                showContextMenu()
            }

            is CommonEvent.OnDropdownPress -> {
                showContextMenu()
            }

            is CommonEvent.OnDropdownDismiss -> {
                closeContextMenu()
            }

            is CommonEvent.OnDropdownValueChanged -> {
                changeContextMenuOption(event.selectionOptionIndex)
            }

            is CommonEvent.OnRadioButtonClick -> {
                updateUserRightsRadioButtonState(event)
            }

            is CommonEvent.OnClickTriState -> {
                toggleTriState()
            }

            is CommonEvent.OnClickCheckbox -> {
                toggleUserAgreementCheckbox(event.index)
            }

            is CommonEvent.OnSubmit -> {
               // TODO: implement submit form processing logic
            }

        }
    }

    /**
     * Closes all screens.
     * TODO: after ScreenState class is created, refactor
     */
    private fun closeAllScreens() {
            _uiState.update {
                it.copy(
                    isFormScreenOpen = false,
                    isApiScreenOpen = false,
                    isListScreenOpen = false,
                    isMenuScreenOpen = false,
                    selectedMenuItem = null
                )
        }
    }

    /**
     * Gets the data from the API and saves it to the state.
     * Updates the apiConnectionMessage state with the result of the connection.
     */
    private fun getApiResult(event: CommonEvent.GetApiResult) {
        val link = event.apiLink
        viewModelScope.launch {
            saveDataToState(link)
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    apiConnectionMessage = getApiConnectionMessage()
                )
            }
        }
    }

    /**
     * Updates the uiState with the selected menu item.
     * Shows the selected screen and closes previous screen.
     * If the selected menu item is "Menu", it toggles the drawer but doesn't close actual screen.
     */
    private fun handleSelectBottomMenuItem(menuItem: String) {
        when (menuItem) {
            "Menu" -> {
                _uiState.update {
                    it.copy(
                        isDrawerOpen = !it.isDrawerOpen,
                        isMenuButtonSelected = !it.isMenuButtonSelected
                    )
                }
            }
            "Form" -> {
                closeActualScreen(menuItem, _uiState.value.actualScreen)
                viewModelScope.launch {
                    if (_uiState.value.selectedMenuItem!= null){
                        delay(300L)
                    }
                    _uiState.update {
                        it.copy(
                            isFormScreenOpen = true,
                            isFormButtonSelected = true,
                            actualScreen = menuItem,
                            selectedMenuItem = menuItem
                        )
                    }
                }
            }
            "Api" -> {
                closeActualScreen(menuItem, _uiState.value.actualScreen)
                viewModelScope.launch{
                    if (_uiState.value.selectedMenuItem!= null){
                        delay(300L)
                    }
                    _uiState.update {
                        it.copy(
                            isApiScreenOpen = true,
                            isApiButtonSelected = true,
                            actualScreen = menuItem,
                            selectedMenuItem = menuItem
                        )
                    }
                }
            }
            "List" -> {
                closeActualScreen(menuItem, _uiState.value.actualScreen)
                viewModelScope.launch{
                    if (_uiState.value.selectedMenuItem!= null){
                        delay(300L)
                    }
                    _uiState.update {
                        it.copy(
                            isListScreenOpen = true,
                            isListButtonSelected = true,
                            actualScreen = menuItem,
                            selectedMenuItem = menuItem
                        )
                    }
                }
            }
        }
    }

    /**
     * Closes the actual screen if it is not the same as the selected menu item.
     * TODO: Screen names to enum and refactor
     */
    private fun closeActualScreen(menuItem: String, actualScreen: String) {
        if (menuItem == actualScreen) {
            return
        }
        viewModelScope.launch {
            when (actualScreen) {
                "Form" -> {
                    _uiState.update {
                        it.copy(
                            isFormScreenOpen = false,
                            isFormButtonSelected = false
                        )
                    }
                }
                "Api" -> {
                    _uiState.update {
                        it.copy(
                            isApiScreenOpen = false,
                            isApiButtonSelected = false
                        )
                    }
                }
                "List" -> {
                    _uiState.update {
                        it.copy(
                            isListScreenOpen = false,
                            isListButtonSelected = false
                        )
                    }
                }
            }
        }
    }


    /**
     * Connects to the API and saves the data to the state.
     * Updates the apiConnectionMessage state with the result of the connection.
     */
    private suspend fun saveDataToState(link: String) {
        val result = getData(link)
        if (result.error != null) {
            // Show error message
            println("${result.error}")
            _uiState.update {
                it.copy(
                    userData = null,
                    apiConnectionMessage = "${result.error}"
                )
            }
        } else {
            val userData = result.data
            println(userData)
            _uiState.update {
                it.copy(
                    userData = userData,
                    apiConnectionMessage = "Data načtena"
                )
            }

        }
    }

    /**
     * Returns the apiConnectionMessage state.
     */
    fun getApiConnectionMessage(): String {
        return _uiState.value.apiConnectionMessage
    }


    /**
     * Connects to the API and returns the result.
     * TODO: catch exception for non-responding server or no internet connection
     */
    private suspend fun getData(link: String): ApiResult<List<UserData>> {
        return try {
            val data = httpService.getApiData(link)
            _uiState.update {
                it.copy(
                    userData = data.values.toList()
                )
            }
            ApiResult(data = _uiState.value.userData)
        } catch (e: Exception) {
            ApiResult(error = "Chyba API: nepodařilo se připojit k hostiteli")
        }
    }

    /////////////// Form Screen ///////////////

    private fun updateEmail(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
    }

    private fun updatePassword(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    private fun updateRememberPasswordSwitch() {
        _uiState.update {
            it.copy(
                isSwitchChecked = !it.isSwitchChecked
            )
        }
    }

    private fun showContextMenu() {
        _uiState.update {
            it.copy(
                isContextMenuVisible = true
            )
        }
    }

    private fun closeContextMenu() {
        _uiState.update {
            it.copy(
                isContextMenuVisible = false
            )
        }
    }

    private fun changeContextMenuOption(selectionOptionIndex: Int) {
        _uiState.update {
            it.copy(
                contextMenuValue = selectionOptionIndex
            )
        }
        closeContextMenu()
    }

    private fun updateUserRightsRadioButtonState(event: CommonEvent.OnRadioButtonClick) {
        _uiState.update {
            it.copy(
                userRightsItems = it.userRightsItems.mapIndexed { index, toggleableInfo ->
                    toggleableInfo.copy(
                        isChecked = index == event.index
                    )
                }.toTypedArray()
            )
        }
    }

    /**
     * Toggles the triState checkbox state and updates the checkboxes state.
     */
    private fun toggleTriState() {
        _uiState.update {
            it.copy(
                triState = when (it.triState) {
                    androidx.compose.ui.state.ToggleableState.Indeterminate -> androidx.compose.ui.state.ToggleableState.On
                    androidx.compose.ui.state.ToggleableState.On -> androidx.compose.ui.state.ToggleableState.Off
                    else -> androidx.compose.ui.state.ToggleableState.On
                },
                userAgreementItems = it.userAgreementItems.map { toggleableInfo ->
                    toggleableInfo.copy(
                        isChecked = it.triState == androidx.compose.ui.state.ToggleableState.Off
                    )
                }.toTypedArray()
            )
        }
    }

    /**
     * Toggles the checkbox state and updates the triState checkbox state.
     */
    private fun toggleUserAgreementCheckbox(index: Int) {
        _uiState.update {
            it.copy(
                userAgreementItems = it.userAgreementItems.mapIndexed { i, toggleableInfo ->
                    toggleableInfo.copy(
                        isChecked = if (i == index) {
                            !toggleableInfo.isChecked
                        } else {
                            toggleableInfo.isChecked
                        }
                    )
                }.toTypedArray(),
            )

        }
        updateTriStateFromCheckboxes()
    }

    /**
     * Updates the triState state based on the checkboxes status.
     */
    private fun updateTriStateFromCheckboxes() {
        _uiState.update {
            it.copy(
                triState = when {
                    it.userAgreementItems.all { toggleableInfo ->
                        toggleableInfo.isChecked
                    } -> androidx.compose.ui.state.ToggleableState.On
                    it.userAgreementItems.all { toggleableInfo ->
                        !toggleableInfo.isChecked
                    } -> androidx.compose.ui.state.ToggleableState.Off
                    else -> androidx.compose.ui.state.ToggleableState.Indeterminate
                }
            )
        }
    }
}