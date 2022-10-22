package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.KMMPlayer
import com.makeevrserg.simplekmm.ui.KMMVideoPlayer
import com.makeevrserg.simplekmm.ui.PlayState
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import com.makeevrserg.simplekmm.ui.theme.TextSizes
import com.makeevrserg.simplekmm.ui.theme.Typography
import kotlinx.datetime.LocalTime
import ru.astrainteractive.astralearner.dto.FileDTO
import java.time.format.DateTimeFormatter

@Composable
fun DisplayVideo(
    file: FileDTO,
    state: ScreenState.Video,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit
) {
    println("Video state: $state")
    val videoState = state.state
    val player = state.player
    Box(Modifier.fillMaxSize().background(Colors.colorPrimaryVariant), contentAlignment = Alignment.Center) {
        when (videoState) {
            is PlayState.Paused, is PlayState.Loading -> {
                ShimmerItem(Modifier.fillMaxWidth().height(256.dp))
            }

            is PlayState.Playing -> {
                KMMPlayer(player = player)
            }
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
