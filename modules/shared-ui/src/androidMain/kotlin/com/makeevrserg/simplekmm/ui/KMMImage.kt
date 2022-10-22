package com.makeevrserg.simplekmm.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.makeevrserg.simplekmm.modules.HttpClientModule
import com.makeevrserg.simplekmm.ui.theme.Colors
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
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
    ) {

        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                SubcomposeAsyncImageContent(Modifier.background(Colors.colorShimmer))
            }

            is AsyncImagePainter.State.Error -> {
                errorIndicator()
            }

            else -> {
                loadingIndicator()
            }
        }
    }

}

