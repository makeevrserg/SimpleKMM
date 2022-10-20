package com.makeevrserg.simplekmm.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.makeevrserg.simplekmm.ui.navigation.wrapper.DecomposeNavigation
import com.makeevrserg.simplekmm.ui.presentation.character.CharacterScreen
import com.makeevrserg.simplekmm.ui.presentation.characters.CharactersScreen
import com.makeevrserg.simplekmm.ui.presentation.choose_screen.ChooseScreen

/**
 * Navigator
 */
class NavHostComponent(
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {
    private val navigation = StackNavigation<AppScreen>()
    private val stack = childStack(
        source = navigation,
        initialConfiguration = AppScreen.Initial,
        childFactory = ::createScreenComponent
    )


    /**
     * Factory function to create screen from given ScreenConfig
     */
    private fun createScreenComponent(
        screenConfig: AppScreen,
        componentContext: ComponentContext
    ): Component {
        val decomposeNavigation = DecomposeNavigation(componentContext, navigation)
        return when (screenConfig) {
            is AppScreen.Main -> Component.composeComponent {
                ChooseScreen(decomposeNavigation)
            }

            is AppScreen.Characters -> Component.composeComponent {
                CharactersScreen(decomposeNavigation)
            }

            is AppScreen.Character -> Component.composeComponent {
                CharacterScreen(screenConfig.id, decomposeNavigation)
            }
        }
    }

    /**
     * Renders screen as per request
     */
    @OptIn(ExperimentalDecomposeApi::class)
    @Composable
    override fun render() {
        Children(
            stack = stack,
            animation = stackAnimation(fade() + scale()),
        ) {
            it.instance.render()
        }
    }


}
