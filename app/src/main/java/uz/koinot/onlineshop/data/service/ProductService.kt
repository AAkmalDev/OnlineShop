package uz.koinot.onlineshop.data.service

import uz.koinot.onlineshop.data.api.ApiService
import uz.koinot.onlineshop.data.model.Product
import uz.koinot.onlineshop.data.model.ResponseList
import javax.inject.Inject

class ProductService @Inject constructor(private val apiService: ApiService):BaseService() {
    suspend fun fetchProduct(page:Int):Result<ResponseList<Product>>{
        return createCall { apiService.getProduct(page) }
    }
}