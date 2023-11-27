import android.app.DownloadManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbanking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }

    val onSignInButtonClicked: (String, String) -> Unit = {email, password ->
        Log.i("LoginScreen", "Email: $email, Password: $password")
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillBounds
        )

    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Login ring",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .offset(x = 10.dp, y = (-5).dp)
        )

        Text(text = stringResource(id = R.string.welcome_text),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 28.sp,

            )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.sign_in_continue),
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(id = R.string.label_username)) },
            placeholder = { Text(text = stringResource(id = R.string.hint_enter_password)) },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "User Icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { onSignInButtonClicked(email.text, password.text) },
            shape = RoundedCornerShape(8.dp),
           // colors = ButtonDefaults.buttonColors(backgroundColor = AppBlueColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
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


}
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

fun fetchData( input: String){
    val url = "https://dummyjson.com/auth/login"
    val jsonObjectRequest = JsonObjectRequest(
        DownloadManager.Request.Method.POST, url, null,
        { response ->
            if(response.get("Response")=="False"){
                name.text = "Incorrect detail"
            }else {
                Glide.with(this).load(response.getString("Poster")).into(image)
                plot.text = response.getString("Plot")
                name.text = response.getString("Title")+"\n\n"+"Writer: "+response.getString("Writer")
            }
        },
        { error ->
            Log.d("vol",error.toString())
        }
    )

    requestQueue.add(jsonObjectRequest)
}