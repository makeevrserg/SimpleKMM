package com.makeevrserg.simplekmm.ui

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel:InstanceKeeper.Instance {
    val viewModelScope = object : CoroutineScope {
        private val job: Job
            get() = Job()
        override val coroutineContext: CoroutineContext
            get() = (job as CoroutineContext) + Dispatchers.IO

    }

    override fun onDestroy() {
        clear()
    }
    fun clear() {
        println("ViewModel cleared")
        viewModelScope.cancel()
    }
}