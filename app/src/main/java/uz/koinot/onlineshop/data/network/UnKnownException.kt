package uz.koinot.onlineshop.data.network

import java.lang.Exception

class UnKnownException:Exception() {
    override val message: String?
        get() = "Some Unknown Error Occurred"
}