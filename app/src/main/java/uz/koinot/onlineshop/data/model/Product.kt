package uz.koinot.onlineshop.data.model

data class Product(
    val id: Int,
    val categoryId: Int,
    val descriptionRu: String,
    val descriptionUz: String,
    val expireDate: String,
    val inComPrice: Int,
    val infoId: Int,
    val name: String,
    val photoListId: List<ProductPhoto>,
    val salePrice: Double,
    val userId: Int
)