package com.makeevrserg.simplekmm.android

import android.app.Application
import com.makeevrserg.simplekmm.ui.AndroidContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidContext.context = this
    }
}