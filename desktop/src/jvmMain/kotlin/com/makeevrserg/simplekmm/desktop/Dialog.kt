package com.makeevrserg.simplekmm.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.makeevrserg.simplekmm.ui.theme.Colors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogConfirm(dialogMessage: DialogMessage?) {
    dialogMessage?.let {
        AlertDialog(onDismissRequest = {},
            confirmButton = {
                TextButton({}) {
                    Text("Ok")
                }
            },
            title = {
                Text(it.title)
            }, text = {
                Text(it.description)
            })
    }

}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoadingDialog(){
    AlertDialog(onDismissRequest = {},
        confirmButton = {
        },
        title = {
        }, text = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Colors.colorPrimary, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        })
}