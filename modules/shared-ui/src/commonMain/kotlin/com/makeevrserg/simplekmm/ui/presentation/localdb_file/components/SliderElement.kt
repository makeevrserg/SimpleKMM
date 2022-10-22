package com.makeevrserg.simplekmm.ui.presentation.localdb_file.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import com.makeevrserg.simplekmm.ui.theme.Typography


/**
 * Format seconds to mm:ss format
 */
fun toMinuteSeconds(seconds: Int): String {
    var minute = (seconds / 60).toString()
    var seconds = (seconds % 60).toString()
    if (minute.length < 2) minute = "0$minute"
    if (seconds.length < 2) seconds = "0$seconds"
    return "$minute:$seconds"

}

@Composable
fun SliderElement(
    maxValue: Float,
    currentValue: Float,
    onValueChangeFinished: () -> Unit = {},
    onValueChange: (Float) -> Unit = {},
) {
    val height = 24.dp
    val timeCurrent = toMinuteSeconds(currentValue.toInt() / 1000)
    val timeEnd = toMinuteSeconds(maxValue.toInt() / 1000)
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Slider(
            value = currentValue,
            onValueChange = {
                onValueChange(it)
            },
            onValueChangeFinished = onValueChangeFinished,
            valueRange = 0f..maxValue, colors = SliderDefaults.colors(
                thumbColor = Colors.colorOnPrimary,
                disabledThumbColor = Colors.colorOnPrimary.copy(alpha = 0.5f),
                activeTrackColor = Colors.colorOnPrimary,
                inactiveTrackColor = Colors.colorOnPrimary.copy(alpha = 0.5f),
            ),
            modifier = Modifier
                .weight(1f, true)
                .height(height)
                .clip(RoundedCornerShape(Dimens.S))
        )
        Text("$timeCurrent/$timeEnd", style = Typography.Default, modifier = Modifier.wrapContentWidth())
    }
}
