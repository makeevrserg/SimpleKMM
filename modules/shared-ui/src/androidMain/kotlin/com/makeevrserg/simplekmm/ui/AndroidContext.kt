package com.makeevrserg.simplekmm.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AndroidContext{
    lateinit var context:Context
    lateinit var activity:Activity
}