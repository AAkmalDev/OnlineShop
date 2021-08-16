package uz.koinot.onlineshop.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.koinot.onlineshop.data.model.ResponseObject

interface AuthService {

    @POST("/api/create")
    suspend fun userRegister(@Body userData: String):ResponseObject<String>

    @POST("/api/login")
    suspend fun userLogin(@Body userLogin:String):ResponseObject<String>
}