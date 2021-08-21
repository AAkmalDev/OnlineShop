package uz.koinot.onlineshop.data.repository

import uz.koinot.onlineshop.data.model.Product
import uz.koinot.onlineshop.data.model.ResponseList
import uz.koinot.onlineshop.data.service.ProductService
import uz.koinot.onlineshop.data.service.Result
import javax.inject.Inject

class ProductRepository @Inject constructor(private val service: ProductService) {

    suspend fun getProduct(page: Int): ResponseList<Product>  {
        return when (val result = service.fetchProduct(page)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }
}