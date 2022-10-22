package com.makeevrserg.simplekmm.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun KMMPlayer(modifier: Modifier = Modifier, player: KMMVideoPlayer)

