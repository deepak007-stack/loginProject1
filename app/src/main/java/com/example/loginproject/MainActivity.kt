package com.example.loginproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginproject.screens.HomeScreen
import com.example.loginproject.screens.Login
import com.example.loginproject.session.SessionManager
import com.example.loginproject.ui.theme.LoginProjectTheme

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
                    LoginNavigationGraph(navController)

                }
            }
        }
    }
}

private fun isUserLoggedIn(context: Context): Boolean {
    val mobileNo = SessionManager.getSavedMobileNo(context)
    // Check if the user is logged in based on session data availability
    return !mobileNo.isNullOrBlank()
}

@Composable
fun LoginNavigationGraph(navController: NavHostController) {

    val context = LocalContext.current
    val isLogin = isUserLoggedIn(context)

    LaunchedEffect(Unit) {
        if (isLogin) {
            navController.navigate("home_screen"){
                popUpTo("login_screen"){ inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = "login_screen") {

        composable(route = "login_screen"){
            Login(navController = navController)
        }

        composable(route = "home_screen"){
            HomeScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginProjectTheme {

    }
}