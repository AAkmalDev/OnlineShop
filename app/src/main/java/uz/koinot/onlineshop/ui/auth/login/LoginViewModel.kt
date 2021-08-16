package uz.koinot.onlineshop.ui.auth.login

import androidx.lifecycle.ViewModel
import uz.koinot.onlineshop.data.repository.AuthRepository
import uz.koinot.onlineshop.data.storage.LocalStorage
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val storage: LocalStorage
    ) : ViewModel() {
}