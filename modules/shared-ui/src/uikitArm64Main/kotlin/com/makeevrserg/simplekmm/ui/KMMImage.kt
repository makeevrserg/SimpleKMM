package com.makeevrserg.simplekmm.ui

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import com.makeevrserg.simplekmm.ui.modules.HttpClientModule
import io.ktor.client.call.*
import io.ktor.client.request.*

@Composable
actual fun KMMImage(
    url: String,
    loadingIndicator: @Composable () -> Unit,
    errorIndicator: @Composable () -> Unit,
    modifier: Modifier,
    contentScale: ContentScale
) {
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var isLoaded by remember { mutableStateOf(false) }
    LaunchedEffect(url) {
        bitmap = kotlin.runCatching {
            val byteArray: ByteArray = HttpClientModule.value.get(url).body()
            org.jetbrains.skia.Image.makeFromEncoded(byteArray).toComposeImageBitmap()
        }.getOrNull()
        isLoaded = true
    }
    if (!isLoaded)
        loadingIndicator()
    else {
        val bitmap = bitmap
        if (bitmap != null)
            Image(
                bitmap,
                "contentDescription",
                modifier = modifier,
                contentScale = contentScale
            )
        else errorIndicator()
    }
}