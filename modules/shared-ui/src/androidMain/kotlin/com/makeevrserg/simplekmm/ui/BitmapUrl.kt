package com.makeevrserg.simplekmm.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.makeevrserg.simplekmm.ui.modules.HttpClientModule
import io.ktor.client.call.*
import io.ktor.client.request.*


@Composable
fun KMMImage2(
    url: String,
    loadingIndicator: @Composable () -> Unit,
    errorIndicator: @Composable () -> Unit,
    modifier: Modifier,
    contentScale: ContentScale
){
    BitmapUrl(url,
        onLoading = {
            loadingIndicator()
        }, onError = {
            errorIndicator()
        }, onLoaded = {
            Image(
                it,
                "contentDescription",
                modifier = modifier,
                contentScale = contentScale
            )
        })
}

@Composable
fun BitmapUrl(
    url: String,
    onLoading: @Composable () -> Unit,
    onError: @Composable () -> Unit,
    onLoaded: @Composable (ImageBitmap) -> Unit
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoaded by remember { mutableStateOf(false) }
    LaunchedEffect(url) {
        bitmap = kotlin.runCatching {
            val byteArray: ByteArray = HttpClientModule.value.get(url).body()
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }.getOrNull()
        isLoaded = true
    }
    if (!isLoaded)
        onLoading()
    else {
        bitmap?.asImageBitmap()?.let {
            onLoaded(it)
        } ?: onError()
    }
}