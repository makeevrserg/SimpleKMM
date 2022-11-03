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
    private var isDestroying = false
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

        player?.stop()
        player?.release()
        player?.removeListener(this)
        initializePlayer()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                event.onStateChanged(PlayerState.Buffering)
            }

            Player.STATE_READY -> {
                event.onStateChanged(PlayerState.Prepared)
                player?.duration?.let {
                    event.onDurationAccessible(it)
                }

            }

            Player.STATE_IDLE -> {
                event.onStateChanged(PlayerState.Paused)
            }
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        event.onStateChanged(if (isPlaying) PlayerState.Playing else return)
    }

    actual fun seek(progress: Float) {
        event.onStateChanged(PlayerState.Buffering)
        player?.seekTo(progress.toLong())
    }

    private fun initializePlayer() {
        if (isDestroying) return
        player = ExoPlayer.Builder(LateinitContext.value)
            .build().also { player ->
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
                player.addListener(this)
            }
        player?.repeatMode = Player.REPEAT_MODE_ONE
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
        isDestroying = true
        progressJob?.cancel()
        player?.stop()
        player?.release()
        player?.removeListener(this)
    }

    init {
        initializePlayer()
    }

}

