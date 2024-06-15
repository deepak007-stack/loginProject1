package com.example.loginproject.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.loginproject.model.HomeScreenResponse
import com.example.loginproject.network.HomeScreenReq
import com.example.loginproject.screens.myDataList.MyList
import com.example.loginproject.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    val response = getData()

//    val dataList = response.value?.data
//    val IMSI = SessionManager.getSavedIMSI(context)        // IMSI
//    val name = SessionManager.getSavedUsername(context)     // name
//    val mobileNo = SessionManager.getSavedMobileNo(context)  // mobileNo
//    val city = SessionManager.getSavedCity(context)             // city

    Surface(color = MaterialTheme.colorScheme.primary) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "SVG Express")

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
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
            ScreenContent(it, navController)
        }
    }
}

@Composable
fun ScreenContent(paddingValues: PaddingValues, navController: NavHostController) {

    val context = LocalContext.current
    val response = getData()

    when (val state = response.value) {

        is DataState.Success -> {
            state.data?.let { loadedData ->
                val dataList = loadedData.data ?: emptyList()
                MyList(dataList, paddingValues, navController)
            }
        }

        is DataState.Error -> {
            Text(text = "Exception is occuring while loading data ")
        }

        DataState.Loading -> {
            Text(text = "data is loading....")
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun getData(): MutableState<DataState<HomeScreenResponse?>> {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val mobileNo = SessionManager.getSavedMobileNo(context)
    val dataState = remember { mutableStateOf<DataState<HomeScreenResponse?>>(DataState.Loading) }

    if (mobileNo != null) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val response =
                    mobileNo.let { it1 -> HomeScreenReq.loadDataForList(it1, null, "ALL") }
                dataState.value = DataState.Success(response)
            } catch (e: Exception) {
                dataState.value = DataState.Error(e)
                Log.e("getData", "Failed to fetch data", e)
            }
        }
    }
    return dataState
}

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
}
