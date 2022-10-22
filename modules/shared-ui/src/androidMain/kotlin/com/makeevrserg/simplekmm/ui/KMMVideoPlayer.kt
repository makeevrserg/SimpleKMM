package com.makeevrserg.simplekmm.ui

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.makeevrserg.simplekmm.ui.player.IPlayerEvent
import com.makeevrserg.simplekmm.ui.player.PlayerState
import kotlinx.coroutines.*


actual class KMMVideoPlayer actual constructor(actual val url: String, actual val event: IPlayerEvent) :
    Player.Listener {
    private var isErrorNotificationSend = false
    private var progressJob: Job? = null
    private val mediaItem: MediaItem
        get() = MediaItem.fromUri(url)
    var player: Player? = null
        private set

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        if (!isErrorNotificationSend) {
            event.onPlayBackError()
            isErrorNotificationSend = true
        }

        destroyPlayer()
        initializePlayer()
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        event.onStateChanged(if (isPlaying) PlayerState.Playing else return)
    }


    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == ExoPlayer.STATE_READY) {
            val duration = player?.duration ?: return
            event.onDurationAccessible(duration)
        }
    }

    actual fun seek(progress: Float) {
        player?.seekTo(progress.toLong())
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(AndroidContext.context)
            .build().also { player ->
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
                player.addListener(this)
            }
        progressJob = MainScope().launch(Dispatchers.Unconfined) {
            while (this.isActive) {
                delay(1000L)
                withContext(Dispatchers.Main) {
                    player?.currentPosition?.let(event::onPlay)
                }
            }
        }
    }

    actual fun destroyPlayer() {
        progressJob?.cancel()
        player?.stop()
        player?.release()
        player?.removeListener(this)
    }

    init {
        initializePlayer()
    }

}

