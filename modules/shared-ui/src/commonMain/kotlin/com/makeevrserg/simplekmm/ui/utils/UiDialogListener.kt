package com.makeevrserg.simplekmm.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.makeevrserg.simplekmm.ui.core.dialog.IUIDialogAction
import com.makeevrserg.simplekmm.ui.core.dialog.UIDialogMessage


object UiDialogListener {
    @Composable
    fun collectUiDialog(action: IUIDialogAction, dialog: @Composable (UIDialogMessage) -> Unit) {
        val currentDialog = action.uiDialog.collectAsState()
        currentDialog.value?.let{
            dialog(it)
        }
    }
}