package com.makeevrserg.simplekmm.ui.utils

import ru.astrainteractive.astralearner.dto.FileDTO

object Injector {
    val _remembered = mutableSetOf<Any>()
    inline fun <reified T : Any> remember(obj: T) {
        _remembered.removeAll { it is T }
        _remembered.add(obj)
    }

    inline fun <reified T : Any> get(): T = _remembered.first { it is T } as T
}