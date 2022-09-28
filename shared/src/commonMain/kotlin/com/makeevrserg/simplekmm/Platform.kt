package com.makeevrserg.simplekmm

import okio.ByteString

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun bsonToString(byteArray: ByteArray): String

expect fun stringToBson(string: String): ByteString