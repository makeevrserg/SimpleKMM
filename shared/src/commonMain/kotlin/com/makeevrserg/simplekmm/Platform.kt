package com.makeevrserg.simplekmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform