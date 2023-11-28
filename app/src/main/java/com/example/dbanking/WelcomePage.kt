import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbanking.R
import com.example.dbanking.ui.theme.Pink40
import com.example.dbanking.ui.theme.SignInContinueColor
import com.example.dbanking.ui.theme.WelcomeMessageColor

@Composable
fun WelcomePage() {

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.mipmap.ic_launcher_background1),
            contentScale = ContentScale.FillBounds
        )

    ) {

        Image(
            painter = painterResource(id = R.mipmap.ic_logout),
            contentDescription = "Logout ring",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .offset(x = 10.dp, y = (-5).dp)
        )

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
                fontSize = 28.sp,
                color = WelcomeMessageColor
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

    }


}

@Preview
@Composable
fun WelcomePagePreview() {
    WelcomePage()
}