package com.example.dbanking.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dbanking.network.LoginResponse
import kotlinx.coroutines.launch

class SignInViewModel(val userRepository: UserRepository) : ViewModel() {

    var loginResponse: LoginResponse by mutableStateOf(LoginResponse(""))
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)

        fun doLogin(username: String, password: String) {
            isLoading = true
        viewModelScope.launch {
            try {
                loginResponse = UserRepository.doLogin(User(username, password))
                state = 1
                loginResponse.isSuccess = true
                isLoading = false
            }
            catch (exception: Exception){

                state = 1
                loginResponse.isSuccess = false
                loginResponse.message = exception.message
                isLoading = false
            }
        }
    }
}

class SignInViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}