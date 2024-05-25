package com.example.mypocapp.model.event

import kotlinx.coroutines.CoroutineScope

/**
 * Sealed class for all events in the app.
 */
sealed interface CommonEvent {
    data class BottomBarButtonClicked(val menuItem: String) : CommonEvent
    data class GetApiResult(val apiLink: String, val coroutineScope: CoroutineScope) : CommonEvent
    data object DismissScreen : CommonEvent
    data class OnEmailChanged(val value: String) : CommonEvent
    data class OnPasswordChanged(val value: String) : CommonEvent
    data class OnDropdownValueChanged(val selectionOptionIndex: Int) : CommonEvent
    data class OnRadioButtonClick(val index: Int) : CommonEvent
    data class OnClickCheckbox(val index: Int) : CommonEvent
    data object OnDropdownDismiss : CommonEvent
    data object OnSwitchChanged : CommonEvent
    data object OnDropdownLongPress : CommonEvent
    data object OnDropdownPress : CommonEvent
    data object OnClickTriState : CommonEvent
    data object OnSubmit : CommonEvent
}