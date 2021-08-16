package uz.koinot.onlineshop.data.model

data class ResponseObject<T>(
    var success: Int,
    var message: String,
    var `object`: T?,
)