package com.example.dbanking.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbanking.R
import com.example.dbanking.ui.theme.AppFocusColor
import com.example.dbanking.ui.theme.AppUnFocusedColor
import com.example.dbanking.ui.theme.SignInContinueColor
import com.example.dbanking.ui.theme.WelcomeMessageColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import com.example.dbanking.ui.theme.ButtonBackgroundColor
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dbanking.dashboard.DashBoardRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    OnSignInClicked: (email: String, password: String) -> Unit,
    OnSkipClicked: () -> Unit
    ) {
        var isChecked by remember { mutableStateOf(false) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        val signUpViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory())
        val context = LocalContext.current
        val onSignInButtonClicked: (String, String) -> Unit = {email, password ->

        signUpViewModel.doLogin(email,password)

        }

        Box(modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.mipmap.ic_launcher_background1),
                contentScale = ContentScale.FillBounds
            )

        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Coop Logo",
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                Text(
                    text = stringResource(id = R.string.welcome_message),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                    color = WelcomeMessageColor
                )

                Spacer(modifier = Modifier.height(100.dp))

                
                Text(
                    text = stringResource(id = R.string.sign_in_continue),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    color = SignInContinueColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = stringResource(id = R.string.label_username)) },
                    placeholder = { Text(text = stringResource(id = R.string.hint_enter_username)) },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = AppFocusColor,
                        unfocusedBorderColor = AppUnFocusedColor
                    ),
                    leadingIcon = {
                        Icon(
                            //painter = painterResource(id = R.mipmap.ic_user),
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "User Icon",
                            tint = AppFocusColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(24.dp))
                val visibilityIcon = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.label_password)) },
                    placeholder = { Text(text = stringResource(id = R.string.hint_enter_password)) },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = AppFocusColor,
                        unfocusedBorderColor = AppUnFocusedColor
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Email Icon",
                            tint = AppFocusColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = visibilityIcon, description)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))
                Button(

                    onClick = { onSignInButtonClicked(email.text, password.text) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBackgroundColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {

                    if (signUpViewModel.isLoading) {
                        CircularProgressIndicator(color = Color.Cyan)
                    } else {
                        Text(
                            text = stringResource(id = R.string.login),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }

                if (signUpViewModel.state != 0){
                    if (signUpViewModel.loginResponse.isSuccess == true){
                        OnSignInClicked(email.text, "")
                        Toast.makeText(context,"success",Toast.LENGTH_LONG).show()
                        signUpViewModel.state = 0

                        DashBoardRoute(email)
                    }
                    else{
                        Toast.makeText(context,signUpViewModel.loginResponse.message,Toast.LENGTH_LONG).show()
                        signUpViewModel.state = 0
                    }
                }

            }

        }
 }

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(OnSignInClicked = { _, _ ->
    },
        OnSkipClicked = {
        })
}




