package com.example.dbanking.signin

import com.example.dbanking.network.LoginResponse
import com.example.dbanking.network.RetrofitClientInstance

object UserRepository {

     private val apiService = RetrofitClientInstance.apiService

    suspend fun doLogin(user: User): LoginResponse {
        return apiService.login(user)
    }

}