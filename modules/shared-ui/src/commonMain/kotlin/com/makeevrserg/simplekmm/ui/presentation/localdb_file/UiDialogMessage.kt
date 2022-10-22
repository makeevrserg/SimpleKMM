package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.ui.ui.UIDialogMessage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UiDialogMessage(message: UIDialogMessage) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(message.title, style = Typography.H1)
        },
        text = {
            Text(message.description, style = Typography.Default)
        },
        confirmButton = {
            TextButton(onClick = {
                message.positiveButton?.onClick?.invoke()
            }) {
                Text(message.positiveButton?.text ?: "", style = Typography.Default)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                message.negativeButton?.onClick?.invoke()
            }) {
                Text(message.negativeButton?.text ?: "", style = Typography.Default)
            }
        }
    )
}