package com.example.loginproject.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.loginproject.model.DetailsData
import com.example.loginproject.model.StatusData
import com.example.loginproject.screens.myDataList.DetailsMyList
import com.example.loginproject.viewmodel.LoginViewModel
import com.example.loginproject.viewmodel.StatusViewModel

@Composable
fun DetailsScreen(
    nid: String?,
    processId: String?,
    caseType: String?,
    viewModel: LoginViewModel,
    navController: NavHostController,
    statusViewModel: StatusViewModel
) {

    val state by viewModel.userDataFlow.collectAsState()
    val statusState by statusViewModel.userDataFlow.collectAsState()

    var userDataList: List<DetailsData>? by remember { mutableStateOf(null) }
    var statusDataList: List<StatusData>? by remember { mutableStateOf(null) }

    var selectedDropDownValue by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.getUser("29", "BVR")              // 3rd API call
    }
    LaunchedEffect(selectedDropDownValue) {
        statusViewModel.getUser("29", "BVR", selectedDropDownValue)     // 4th API call
    }

    LaunchedEffect(state) {
        userDataList = state?.data
    }

    LaunchedEffect(statusState) {
        statusDataList = statusState?.data               // list of status data string
    }

//    Log.d("hello4",statusDataList.toString())

    DetailsMyList(userDataList, navController, onDropDownValueChanged = { value ->
        selectedDropDownValue = value
    }, statusDataList)

}

//@Preview(showBackground = true)
//@Composable
//fun DetailsScreenPreview(modifier: Modifier = Modifier) {
//    val apiServices = DetailsRetrofitReq.services
//    val repository = LoginRepository(apiServices)
//    val vm = LoginViewModel(repository)
//    val navController = rememberNavController()
//    DetailsScreen("123", "1234", "BVR", vm, navController, statusViewModel)
//}

//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun getDataForDetails(processId: String?, caseType: String?): MutableState<DetailsScreenResponse?> {
//
//    val dataState = remember { mutableStateOf<DetailsScreenResponse?>(null) }
//
//    LaunchedEffect(Unit) {
//        if (processId != null && caseType != null) {
////            val response = loadDataForDetailsAsync(processId, caseType)
////            dataState.value = response
////            Log.d("svg", dataState.toString())
//        }
//    }
////    Log.d("svg", dataState.toString())
//    return dataState
//
////    val coroutineScope = rememberCoroutineScope()
////    val context = LocalContext.current
////    val dataState = remember { mutableStateOf<DetailsScreenResponse?>(null) }
////
////    coroutineScope.launch(Dispatchers.IO) {
////        if (processId != null && caseType != null) {
//////            && processId.isNotBlank() && caseType.isNotBlank() && !processId.isNullOrEmpty() && !caseType.isNullOrEmpty()
////            val response = DetailsRetrofitReq.loadDataForDetails(processId, caseType)
////            dataState.value = response
////            Log.d("svg",dataState.toString())
////            return@launch
////        }
////    }
////
////    Log.d("svg1",dataState.toString())
////    return dataState
//}

//private suspend fun loadDataForDetailsAsync(
//    processId: String,
//    caseType: String
//): DetailsScreenResponse? =
//    suspendCoroutine { continuation ->
//        // Launching a coroutine
//        CoroutineScope(Dispatchers.IO).launch {
//            // Making API call
////            val response = DetailsRetrofitReq.loadDataForDetails(processId, caseType)
//            // Resuming the continuation with the response
////            continuation.resume(response)
//        }
//    }

