package com.makeevrserg.simplekmm

import okio.ByteString

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun bsonToString(byteArray: ByteArray): String = ""
actual fun stringToBson(string: String): ByteString {
    return ByteString.EMPTY
}