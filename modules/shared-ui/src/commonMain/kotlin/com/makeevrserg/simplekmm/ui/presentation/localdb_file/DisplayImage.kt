package com.makeevrserg.simplekmm.ui.presentation.localdb_file

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.localb_api.LocalDBRoutes
import com.makeevrserg.simplekmm.ui.KMMImage
import com.makeevrserg.simplekmm.ui.navigation.AppScreen
import com.makeevrserg.simplekmm.ui.presentation.localdb_files.FileDTOPreview
import com.makeevrserg.simplekmm.ui.presentation.localdb_files.thumbnailUrl
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import com.makeevrserg.simplekmm.ui.utils.Injector
import ru.astrainteractive.astralearner.dto.FileDTO

@Composable
fun DisplayImage(file: FileDTO) {
    val url = LocalDBRoutes.staticPath("files/${file.id}")
    println("DisplayImage: $url")
    Box(Modifier.padding(Dimens.XXS)) {
        KMMImage(url,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            loadingIndicator = {
                ShimmerItem(Modifier.fillMaxSize())
            },
            errorIndicator = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "404",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Colors.colorOnPrimary
                    )
                }

            })
    }
}
