package com.makeevrserg.simplekmm.ui

import androidx.compose.runtime.Composable

@Composable
expect fun KMMImage(
    url: String,
    loadingIndicator: @Composable () -> Unit ,
    errorIndicator: @Composable () -> Unit,
)