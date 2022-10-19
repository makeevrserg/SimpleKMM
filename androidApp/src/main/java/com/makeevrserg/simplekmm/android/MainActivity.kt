package com.makeevrserg.simplekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.makeevrserg.simplekmm.KMMApplication
import com.makeevrserg.simplekmm.ui.CharacterListViewModel
import com.makeevrserg.simplekmm.ui.characters.CharactersScreen
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.disk.DiskCacheBuilder
import com.seiko.imageloader.cache.memory.MemoryCacheBuilder
import okio.Path.Companion.toOkioPath

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
                    CompositionLocalProvider(
                        LocalImageLoader provides generateImageLoader()
                    ) {
                        CharactersScreen(KMMApplication()){}
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