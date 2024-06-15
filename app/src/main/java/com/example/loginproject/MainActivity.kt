package com.example.loginproject

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginproject.cameraSetUp.components.MyImageArea
import com.example.loginproject.cameraSetUp.viewmodel.MainViewModel
import com.example.loginproject.screens.DetailsScreen
import com.example.loginproject.screens.HomeScreen
import com.example.loginproject.screens.Login
import com.example.loginproject.session.SessionManager
import com.example.loginproject.ui.theme.LoginProjectTheme
import com.example.loginproject.viewmodel.LoginViewModel
import com.example.loginproject.viewmodel.LoginViewModelFactory
import com.example.loginproject.viewmodel.StatusViewModel
import com.example.loginproject.viewmodel.StatusViewModelFactory
import java.io.File
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var statusViewModel: StatusViewModel
    val cameraViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        cameraViewModel.setContext(this)

        setContent {
            LoginProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val repository = (application as LoginApplication).loginRepository
                    viewModel = ViewModelProvider(this,LoginViewModelFactory(repository))[LoginViewModel::class.java]

                    val statusRepository = (application as LoginApplication).statusRepository
                    statusViewModel = ViewModelProvider(this,StatusViewModelFactory(statusRepository))[StatusViewModel::class.java]

                    MyScreen(viewModel,statusViewModel)

                }
            }
        }
    }
}

@Composable
fun MyScreen(viewModel: LoginViewModel, statusViewModel: StatusViewModel) {

    val navController = rememberNavController()
    LoginNavigationGraph(navController, viewModel, statusViewModel)
}

@Composable
fun LoginNavigationGraph(
    navController: NavHostController,
    viewModel: LoginViewModel,
    statusViewModel: StatusViewModel
) {

    val context = LocalContext.current
    val isLogin = isUserLoggedIn(context)

    LaunchedEffect(Unit) {
        if (isLogin) {
            navController.navigate("home_screen") {
                popUpTo("login_screen") { inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = "login_screen") {

        composable(route = "login_screen") {
            Login(navController = navController)
        }

        composable(route = "home_screen") {
            HomeScreen(navController)
        }

        composable(route = "details_screen/{nid}/{processId}/{caseType}") { it ->
            val nid = it.arguments?.getString("nid")
            val processId = it.arguments?.getString("processId")
            val caseType = it.arguments?.getString("caseType")

            DetailsScreen(nid, processId, caseType, viewModel, navController, statusViewModel)
        }
    }
}

fun isUserLoggedIn(context: Context): Boolean {
    val mobileNo = SessionManager.getSavedMobileNo(context)
    // Check if the user is logged in based on session data availability
    return !mobileNo.isNullOrBlank()
}


