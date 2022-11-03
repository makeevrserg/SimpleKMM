package com.makeevrserg.simplekmm.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.makeevrserg.simplekmm.ui.theme.Colors

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

