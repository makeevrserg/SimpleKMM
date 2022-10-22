package com.makeevrserg.simplekmm.ui.presentation.localdb_files

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.simplekmm.localb_api.LocalDBRoutes
import com.makeevrserg.simplekmm.ui.KMMImage
import com.makeevrserg.simplekmm.ui.components.ShimmerItem
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
import ru.astrainteractive.astralearner.dto.FileDTO
import ru.astrainteractive.astralearner.dto.FileType

val FileDTO.thumbnailUrl: String
    get() = LocalDBRoutes.staticPath("files/${this.id}?thumbnail=true")

@Composable
fun FileDTOPreview(
    modifier: Modifier = Modifier.size(64.dp),
    contentScale: ContentScale = ContentScale.Crop,
    fileDTO: FileDTO,
    onClick: () -> Unit
) {
    Box(modifier.padding(Dimens.XXS).clickable(onClick = onClick)) {
        Box {
            KMMImage(fileDTO.thumbnailUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = contentScale,
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
        val color = when (fileDTO.type) {
            FileType.WEBM, FileType.MP4,FileType.MKV,FileType.MOV,FileType.M4V -> Colors.videoColor
            FileType.GIF, FileType.PNG, FileType.JPG, FileType.JPEG -> Colors.imageColor
            FileType.UNKNOWN -> Colors.colorNegative
        }
        Box(Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(color)) {
            Text(
                fileDTO.type.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Colors.colorOnPrimary
            )
        }
    }
}