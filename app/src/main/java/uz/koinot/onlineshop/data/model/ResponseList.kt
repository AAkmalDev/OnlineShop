package uz.koinot.onlineshop.data.model

data class ResponseList<T>(
    var success: Int,
    var message: String,
    var `object`: List<T>?,
)