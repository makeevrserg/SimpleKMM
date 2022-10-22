package com.makeevrserg.simplekmm.ui.presentation.localdb_files

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Typography

@Composable
fun FilledButton(text: String, enabled: Boolean, onClick: (enabled: Boolean) -> Unit) {
    val backgroundColor = if (enabled) Colors.colorSecondary else Colors.colorPrimaryVariant
    val textColor = if (enabled) Colors.colorPrimaryVariant else Colors.colorOnPrimary
    Button(
        onClick = {
            onClick.invoke(enabled)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(text, style = Typography.Default.copy(color = textColor))
    }
}