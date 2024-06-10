package com.example.loginproject.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginproject.model.HomeScreenResponse
import com.example.loginproject.network.HomeScreenReq
import com.example.loginproject.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val response = getData()
    val dataList = response.value?.data ?: emptyList()

//    val IMSI = SessionManager.getSavedIMSI(context)        // IMSI
//    val name = SessionManager.getSavedUsername(context)     // name
//    val mobileNo = SessionManager.getSavedMobileNo(context)  // mobileNo
//    val city = SessionManager.getSavedCity(context)             // city

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top App Bar")

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Mic,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        }
    ) {

        Log.d("hello1", dataList.toString())
        MyList(dataList, it)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun getData(): MutableState<HomeScreenResponse?> {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val mobileNo = SessionManager.getSavedMobileNo(context)  // mobileNo
    val dataState = remember { mutableStateOf<HomeScreenResponse?>(null) }

    if (mobileNo != null) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val response =
                    mobileNo?.let { it1 -> HomeScreenReq.loadDataForList(it1, null, "ALL") }
                dataState.value = response
            } catch (e: Exception) {
                Log.e("data", "is not fetching", e)
            }
        }
    }
    return dataState
}

@Preview()
@Composable
fun Preview(modifier: Modifier = Modifier) {
//    HomeScreen()
}