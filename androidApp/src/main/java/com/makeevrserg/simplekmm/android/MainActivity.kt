package com.makeevrserg.simplekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.simplekmm.ui.AndroidContext
import com.makeevrserg.simplekmm.ui.navigation.NavHostComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidContext.activity = this

        val root =
            NavHostComponent(
                componentContext = defaultComponentContext(),
            )

        setContent {
            MaterialTheme {
                root.render()

            }
        }
    }
}