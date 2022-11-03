package com.makeevrserg.simplekmm.ui.core.dialog

import kotlinx.coroutines.flow.MutableStateFlow

interface IUIDialogAction {
    val uiDialog: MutableStateFlow<UIDialogMessage?>

    fun UIDialogMessage.send() {
        uiDialog.value = this
    }

    fun clearUiDialog() {
        uiDialog.value = null
    }
}