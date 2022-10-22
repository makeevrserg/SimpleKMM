package com.makeevrserg.simplekmm.ui.presentation.localdb_file.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.makeevrserg.simplekmm.localb_api.LocalDBRoutes
import com.makeevrserg.simplekmm.ui.KMMImage
import com.makeevrserg.simplekmm.ui.components.ShimmerItem
import com.makeevrserg.simplekmm.ui.theme.Colors
import com.makeevrserg.simplekmm.ui.theme.Dimens
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
