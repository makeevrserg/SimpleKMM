package com.makeevrserg.simplekmm.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState


object UiDialogListener {
    @Composable
    fun collectUiDialog(action: IUIDialogAction, dialog: @Composable (UIDialogMessage) -> Unit) {
        val currentDialog = action.uiDialog.collectAsState()
        currentDialog.value?.let{
            dialog(it)
        }
    }
}