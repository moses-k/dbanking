package com.example.dbanking.network


data class LoginResponse(val string: String){
    var isSuccess: Boolean? =null
    var message: String?= null
    var body: String?= null
}
