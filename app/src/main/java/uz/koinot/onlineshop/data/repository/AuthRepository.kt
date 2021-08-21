package uz.koinot.onlineshop.data.repository

import uz.koinot.onlineshop.data.api.AuthService
import uz.koinot.onlineshop.data.model.ResponseObject
import uz.koinot.onlineshop.data.model.User
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthService
) {

    suspend fun userRegister(
        userData: User,
    ): ResponseObject<String>? {
        val data = api.userRegister(userData)
            if (data.success == 200) {
                return data
            }
            return null
    }
}