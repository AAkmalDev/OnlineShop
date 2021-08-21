package uz.koinot.onlineshop.data.network

import java.io.IOException

class NotFoundException : IOException() {
    override val message: String?
        get() = "Not Found"
}