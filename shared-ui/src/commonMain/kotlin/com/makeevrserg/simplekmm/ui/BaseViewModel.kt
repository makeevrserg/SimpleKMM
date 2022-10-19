package com.makeevrserg.simplekmm.ui

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel {
    val viewModelScope = object : CoroutineScope {
        private val job: Job
            get() = Job()
        override val coroutineContext: CoroutineContext
            get() = (job as CoroutineContext) + Dispatchers.IO

    }

    fun clear() {
        viewModelScope.cancel()
    }
}