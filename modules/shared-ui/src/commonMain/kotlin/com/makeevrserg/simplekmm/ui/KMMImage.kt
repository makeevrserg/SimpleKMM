package com.makeevrserg.simplekmm.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
expect fun KMMImage(
    url: String,
    loadingIndicator: @Composable () -> Unit ,
    errorIndicator: @Composable () -> Unit,
    modifier:Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
)