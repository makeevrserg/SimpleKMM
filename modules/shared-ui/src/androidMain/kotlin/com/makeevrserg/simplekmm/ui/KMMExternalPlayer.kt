package com.makeevrserg.simplekmm.ui

import android.content.Intent
import android.net.Uri


actual class KMMExternalPlayer actual constructor(actual val url: String) {
    init {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(url), "video/*")
        println("Asking to play url: $url")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        LateinitActivity.value.startActivity(Intent.createChooser(intent, "Complete action using"))
    }
}