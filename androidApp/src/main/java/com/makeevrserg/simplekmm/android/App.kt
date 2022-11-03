package com.makeevrserg.simplekmm.android

import android.app.Application
import com.makeevrserg.simplekmm.ui.LateinitContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LateinitContext.initialize(this)
    }
}