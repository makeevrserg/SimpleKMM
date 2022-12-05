package com.makeevrserg.simplekmm.ui.core

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(),InstanceKeeper.Instance {
//    val viewModelScope = object : CoroutineScope {
//        private val job: Job
//            get() = Job()
//        override val coroutineContext: CoroutineContext
//            get() = (job as CoroutineContext) + Dispatchers.Unconfined
//
//    }

    override fun onDestroy() {
        clear()
    }

    fun clear() {
        viewModelScope.cancel()
    }
}

