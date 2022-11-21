package com.makeevrserg.simplekmm.ui.navigation.wrapper

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.makeevrserg.simplekmm.ui.core.BaseViewModel
import com.makeevrserg.simplekmm.ui.navigation.AppScreen

class DecomposeNavigation(
    val componentContext: ComponentContext,
    val navigation: StackNavigation<AppScreen>
) : AppScreenNavigation {
    override fun <K : AppScreen> nextScreen(screen: K) {
        navigation.push(screen)
    }

    override fun pop() {
        navigation.pop()
    }

    private fun <T : InstanceKeeper.Instance> InstanceKeeper.getOrCreate(key: Any, factory: () -> T): T {
        var instance: T? = get(key) as? T?
        if (instance == null) {
            instance = factory()
            put(key, instance)
        }

        return instance
    }

}