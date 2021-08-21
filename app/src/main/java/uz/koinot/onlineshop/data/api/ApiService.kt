package uz.koinot.onlineshop.data.api

import retrofit2.Response
import retrofit2.http.GET
import uz.koinot.onlineshop.data.model.Product
import uz.koinot.onlineshop.data.model.ResponseList

interface ApiService  {


    @GET("")
    suspend fun getProduct(page: Int):Response<ResponseList<Product>>
}