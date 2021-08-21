package uz.koinot.onlineshop.data.network

import java.io.IOException

class UnAuthorizedException: IOException() {
    override val message: String?
        get() = "User Unauthorized"
}