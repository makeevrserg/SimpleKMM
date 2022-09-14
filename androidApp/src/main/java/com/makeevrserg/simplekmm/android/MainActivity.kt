package com.makeevrserg.simplekmm.android

import CharacterScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makeevrserg.simplekmm.Greeting
import com.makeevrserg.simplekmm.ui.CharacterListViewModel
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.disk.DiskCacheBuilder
import com.seiko.imageloader.cache.memory.MemoryCacheBuilder
import kotlinx.coroutines.launch
import okio.Path.Companion.toOkioPath

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFFFFFFF)
            ) {
                val viewModel = CharacterListViewModel(Greeting().rickAndMortyAPI)
                CompositionLocalProvider(
                    LocalImageLoader provides generateImageLoader(),
                ) {
                    CharacterScreen(viewModel)
                }
            }
        }
    }

    private fun generateImageLoader(): ImageLoader {
        return ImageLoaderBuilder(this)
            .memoryCache {
                MemoryCacheBuilder(this)
                    // Set the max size to 25% of the app's available memory.
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCacheBuilder()
                    .directory(cacheDir.resolve("image_cache").toOkioPath())
                    .maxSizeBytes(512L * 1024 * 1024) // 512MB
                    .build()
            }
            .build()
    }
}