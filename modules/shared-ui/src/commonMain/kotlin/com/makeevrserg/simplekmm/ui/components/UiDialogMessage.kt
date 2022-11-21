package com.makeevrserg.simplekmm.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.makeevrserg.simplekmm.ui.theme.Typography
import com.makeevrserg.simplekmm.ui.core.dialog.UIDialogMessage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UiDialogMessage(message: UIDialogMessage) {
    Column(
        Modifier.zIndex(Float.MAX_VALUE),
    ) {
        Text(message.title, style = Typography.H1)
        Text(message.description, style = Typography.Default)
        TextButton(onClick = {
            message.positiveButton?.onClick?.invoke()
        }) {
            Text(message.positiveButton?.text ?: "", style = Typography.Default)
            TextButton(onClick = {
                message.negativeButton?.onClick?.invoke()
            }) {
                Text(message.negativeButton?.text ?: "", style = Typography.Default)
            }
        }


    }
}