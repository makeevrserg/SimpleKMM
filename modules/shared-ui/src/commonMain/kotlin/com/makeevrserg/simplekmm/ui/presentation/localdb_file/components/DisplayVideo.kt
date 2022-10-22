package com.makeevrserg.simplekmm.ui.presentation.localdb_file.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.KMMImage
import com.makeevrserg.simplekmm.ui.KMMPlayer
import com.makeevrserg.simplekmm.ui.components.ShimmerItem
import com.makeevrserg.simplekmm.ui.player.PlayerState
import com.makeevrserg.simplekmm.ui.presentation.localdb_file.ScreenState
import com.makeevrserg.simplekmm.ui.presentation.localdb_files.thumbnailUrl
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import ru.astrainteractive.astralearner.dto.FileDTO

@Composable
fun DisplayVideo(
    file: FileDTO,
    state: ScreenState.Video,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit
) {
    val videoState = state.state
    val player = state.player
    Box(Modifier.fillMaxSize().background(Colors.colorPrimaryVariant), contentAlignment = Alignment.Center) {

        when (videoState) {
            PlayerState.Loading -> {
                KMMImage(file.thumbnailUrl,
                    modifier = Modifier.fillMaxWidth().height(256.dp),
                    contentScale = ContentScale.Fit,
                    loadingIndicator = {
                        ShimmerItem(Modifier.fillMaxWidth().height(256.dp))
                    },
                    errorIndicator = {
                        Box(Modifier.fillMaxWidth().height(256.dp), contentAlignment = Alignment.Center) {
                            Text(
                                "404",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Colors.colorOnPrimary
                            )
                        }

                    })
//                ShimmerItem(Modifier.fillMaxWidth().height(256.dp))
            }
            PlayerState.Paused, PlayerState.Playing -> KMMPlayer(player = player)
        }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            SliderElement(state.duration.toFloat(), state.currentPosition.toFloat(),
                onValueChange = {
                    onValueChange(it)
                },
                onValueChangeFinished = {
                    onValueChangeFinished()
                })
            Spacer(Modifier.height(Dimens.XXXL))
        }
    }

}


