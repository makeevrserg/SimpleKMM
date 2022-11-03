package com.makeevrserg.simplekmm.ui.utils

object Injector {
    val _remembered = mutableSetOf<Any>()
    inline fun <reified T : Any> remember(obj: T) {
        _remembered.removeAll { it is T }
        _remembered.add(obj)
    }

    inline fun <reified T : Any> get(): T = _remembered.first { it is T } as T
    inline fun <reified T : Any> getAndDelete(): T? {
        val value = _remembered.firstOrNull { it is T }
        _remembered.remove(value)
        return value as? T?
    }
}