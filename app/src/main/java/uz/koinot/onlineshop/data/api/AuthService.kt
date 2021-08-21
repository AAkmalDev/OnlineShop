package uz.koinot.onlineshop.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import uz.koinot.onlineshop.data.model.ResponseObject
import uz.koinot.onlineshop.data.model.User

interface AuthService {

    @POST("api/create")
    suspend fun userRegister(@Body userData: User):ResponseObject<String>

    @POST("api/login")
    suspend fun userLogin(@Body userLogin:String):ResponseObject<String>
}