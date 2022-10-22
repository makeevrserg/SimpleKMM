package com.makeevrserg.simplekmm.ui

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import io.ktor.client.call.*
import io.ktor.client.request.*

@Composable
actual fun KMMImage(
    url: String,
    loadingIndicator: @Composable () -> Unit,
    errorIndicator: @Composable () -> Unit
) {
    var state by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Loading(null)) }
    if (state is AsyncImagePainter.State.Error)
        errorIndicator()
    else if (state is AsyncImagePainter.State.Loading)
        loadingIndicator()
    AsyncImage(
        model = url,
        contentDescription = null,
        onState = {
            when (it) {
                is AsyncImagePainter.State.Error -> state = it
                is AsyncImagePainter.State.Loading -> state = it
                else -> state = it
            }
        }
    )
}