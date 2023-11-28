package com.example.dbanking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dbanking.signin.LoginScreen
import com.example.dbanking.ui.theme.DbankingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DbankingTheme {
                LoginScreen(OnSignInClicked = { _, _ ->
                },OnSkipClicked = {})
            }
        }
    }
}

