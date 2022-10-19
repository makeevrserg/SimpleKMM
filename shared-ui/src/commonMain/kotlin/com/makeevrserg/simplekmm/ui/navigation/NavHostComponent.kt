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
import com.makeevrserg.simplekmm.ui.presentation.character.CharacterScreen
import com.makeevrserg.simplekmm.ui.presentation.characters.CharactersScreen

/**
 * Navigator
 */
class NavHostComponent(
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {
    private val navigation = StackNavigation<AppScreen>()
    private val stack = childStack(
        source = navigation,
        initialConfiguration = AppScreen.Characters,
        childFactory = ::createScreenComponent
    )

    /**
     * Factory function to create screen from given ScreenConfig
     */
    private fun createScreenComponent(
        screenConfig: AppScreen,
        componentContext: ComponentContext
    ): Component {
        return when (screenConfig) {

            is AppScreen.Characters -> object : Component {
                @Composable
                override fun render() {
                    CharactersScreen(
                        componentContext,
                        navigation
                    )
                }
            }

            is AppScreen.Character -> object : Component {
                @Composable
                override fun render() {
                    CharacterScreen(
                        screenConfig.id,
                        componentContext,
                        navigation
                    )
                }

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