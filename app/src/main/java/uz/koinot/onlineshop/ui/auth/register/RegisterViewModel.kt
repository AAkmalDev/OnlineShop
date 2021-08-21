package uz.koinot.onlineshop.ui.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.koinot.onlineshop.data.model.User
import uz.koinot.onlineshop.data.repository.AuthRepository
import uz.koinot.onlineshop.data.storage.LocalStorage
import uz.koinot.onlineshop.utils.UiStateObject
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val storage: LocalStorage
) : ViewModel() {

    private var _registerFlow = MutableStateFlow<UiStateObject<String>>(UiStateObject.EMPTY)
    val registerFlow: StateFlow<UiStateObject<String>> get() = _registerFlow

    fun register(user: User) = viewModelScope.launch {
        _registerFlow.value = UiStateObject.LOADING

        try {
            val data = repository.userRegister(user)
            if (data?.message == "Saved User") {
                _registerFlow.value = UiStateObject.SUCCESS(data.message)
                storage.accessToken = data.`object`!!
            } else {
                _registerFlow.value = UiStateObject.ERROR(data!!.message)
            }
        } catch (e: Exception) {
            _registerFlow.value = UiStateObject.ERROR(e.message ?: "not found")
        }
    }

    fun reRegister() {
        _registerFlow.value = UiStateObject.EMPTY
    }
}