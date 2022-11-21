package com.makeevrserg.simplekmm.ui.utils

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.simplekmm.ui.navigation.wrapper.AppScreenNavigation
import com.makeevrserg.simplekmm.ui.navigation.wrapper.DecomposeNavigation

inline fun <reified T : InstanceKeeper.Instance> AppScreenNavigation.viewModelFactory(block: () -> T): T {
    return (this as DecomposeNavigation).componentContext.instanceKeeper.getOrCreate {
        block()
    }

}