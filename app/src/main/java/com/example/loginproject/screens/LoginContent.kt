package com.example.loginproject.screens

import android.annotation.SuppressLint
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginproject.R
import com.example.loginproject.model.LoginResponse
import com.example.loginproject.network.LoginRetrofitReq
import com.example.loginproject.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Login(navController: NavHostController) {

    var result by remember { mutableStateOf<LoginResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val context1 = LocalContext.current
    val androidId = Settings.Secure.getString(context1.contentResolver, Settings.Secure.ANDROID_ID)


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
        var isNameError by remember { mutableStateOf(false) }
        val usernameFocusRequester = remember { FocusRequester() }
        val passwordFocusRequester = remember { FocusRequester() }

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
                        onValueChange = {
                            username = it
                        },
                        label = { Text("Mobile no") },
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
                            .focusRequester(usernameFocusRequester),
                        isError = isNameError,
                        keyboardActions = KeyboardActions(onNext = {
                            isNameError = username == "" || username.isEmpty() || username.isBlank()
                            if (!isNameError) {
                                passwordFocusRequester.requestFocus()
                            }
                        }),
                        supportingText = {
                            if (isNameError) {
                                Text(text = "Please enter a proper username!")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (username.isNotBlank()) {
                                coroutineScope.launch(Dispatchers.IO) {
                                    try {
                                        val response = LoginRetrofitReq.login(username, androidId)
                                        result = response
                                        if (result?.message == "Success" && result?.data?.isNotEmpty() == true) {

                                            val userData = result!!.data?.get(0)
                                            val name = userData?.name
                                            val city = userData?.city
                                            val imsi = userData?.IMSI
                                            val mobile_no = userData?.mobile

                                            if (!imsi.isNullOrEmpty() && !mobile_no.isNullOrEmpty() && !name.isNullOrEmpty() && !city.isNullOrEmpty()) {
                                                // Save IMSI and MobileNo in the session
                                                SessionManager.saveCredentials(
                                                    context,
                                                    name,
                                                    imsi,
                                                    mobile_no,
                                                    city
                                                )
                                            }

                                            withContext(Dispatchers.Main) {
                                                navController.navigate("home_screen")
                                            }
                                        }
                                    } catch (e: Exception) {
                                        Log.e("Login", "error during", e)
                                    }
                                }
                            }
                        }) {
                        Text("Login")
                    }
                }
            }
        }

        Row {
            Text(text = "Don't have an account?")
            Text(
                text = " Register",
                modifier = Modifier.clickable { },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}