package com.makeevrserg.simplekmm.desktop

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val mail: String = "",
    val phone: String = "",
    val password: String = "",
    val isLoading:Boolean = false
)

data class DialogMessage(
    val title: String,
    val description: String
)

class ViewModel {
    val uiState = MutableStateFlow(UiState())
    val dialogMessage = MutableStateFlow<DialogMessage?>(null)

    fun onMailEntered(mail: String) = uiState.update {
        it.copy(mail = mail)
    }

    fun onPhoneEntered(phone: String) = uiState.update {
        it.copy(phone = phone)
    }

    fun onPasswordEntered(password: String) = uiState.update {
        it.copy(password = password)
    }

    fun onDialogClicked(){
        dialogMessage.value = null
    }

}