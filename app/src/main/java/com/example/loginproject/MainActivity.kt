package com.example.loginproject

import android.icu.number.Scale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.loginproject.screens.LoginContent
import com.example.loginproject.ui.theme.LoginProjectTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current
        // Main card Content for Login

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 20.dp)
            ) {

                // heading
                Text(
                    text = "SVG Express",
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .padding(vertical = 26.dp)
                        .align(Alignment.CenterHorizontally)
                )

                // image
                Image(
                    modifier = Modifier
                        .size(170.dp)
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .border(2.dp, Color.Transparent, CircleShape)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.truck_8716758),
                    contentDescription = "logo"
                )

                // heading login

                Text(
                    text = "Login",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(24.dp),
                    fontWeight = FontWeight.Normal
                )

                //login fields

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        trailingIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "hide"
                                )
                            }
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                Icon(
                                    imageVector = Icons.Default.RemoveRedEye,
                                    contentDescription = "hide"
                                )
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        })
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
//            navController.navigate("main_screen")
                    }) {
                        Text("Login")
                    }
                }

            }
        }
        
        Row {
            Text(text = "Don't have an account?")
            Text(text = " Register", modifier = Modifier.clickable {  }, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginProjectTheme {
        Login()
    }
}