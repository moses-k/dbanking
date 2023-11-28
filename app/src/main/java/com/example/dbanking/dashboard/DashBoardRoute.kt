package com.example.dbanking.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun DashBoardRoute(message: TextFieldValue) {
    DashBoardScreen(message = message)
}