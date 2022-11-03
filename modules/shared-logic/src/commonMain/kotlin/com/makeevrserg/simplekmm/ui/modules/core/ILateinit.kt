package com.makeevrserg.simplekmm.ui.modules.core

abstract class ILateinit<T : Any> {
    fun initialize(value: T) {
        this.value = value
    }

    lateinit var value: T
        private set
}