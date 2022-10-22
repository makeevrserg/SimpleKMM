package com.makeevrserg.simplekmm.ui.ui

/**
 * This class is created for handling ui dialogs from viewmodel by stateFlow or etc
 */
class UIDialogMessage(
    val title: String,
    val description: String,
    val positiveButton: UIDialogButton? = null,
    val negativeButton: UIDialogButton? = null,
    val isCancellable: Boolean = true,
)

class UIDialogButton(
    val text: String,
    val onClick: () -> Unit = {}
)