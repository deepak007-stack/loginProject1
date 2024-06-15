package com.example.loginproject.screens.myDataList

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.loginproject.R
import com.example.loginproject.component.DropdownComponent1
import com.example.loginproject.model.DetailsData
import com.example.loginproject.model.StatusData
import com.example.loginproject.screens.MyModalBottomSheet
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsMyList(
    userDataList: List<DetailsData>?,
    navController: NavHostController,
    onDropDownValueChanged: (String) -> Unit,
    statusDataList: List<StatusData>?
) {

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
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .background(MaterialTheme.colorScheme.surface)
            ) {

                itemsIndexed(userDataList ?: emptyList()) { index, item ->
                    ShowItem1(item, onDropDownValueChanged, statusDataList)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowItem1(
    item: DetailsData,
    onDropDownValueChanged: (String) -> Unit,
    statusDataList: List<StatusData>?
) {

    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isSuccess: Boolean ->
        // Handle the result if needed
        if (isSuccess) {

        } else {
            // Picture capture failed or was canceled
        }
    }

    when (item.FieldType) {

        "TextBox" -> {
            val fieldValue = remember { mutableStateOf(item.FieldValue) }
            OutlinedTextField(
                value = fieldValue.value,
                onValueChange = { fieldValue.value = it },
                label = { Text(item.FieldName, style = MaterialTheme.typography.bodyLarge) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 6.dp, end = 6.dp),
                isError = item.Validate == "YES" && fieldValue.value.isEmpty(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (item.Validate == "YES" && fieldValue.value.isEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            )
        }

        "DropDownList" -> {

            var selectedOption1 by remember { mutableStateOf("select option") }
            var selectedOption by rememberSaveable { mutableStateOf("select option") }

            val options = item.FieldValue.split(",")
            var isExpanded by remember { mutableStateOf(false) }

            if (item.FieldName == "Sub Status" && statusDataList != null) {
                val options = statusDataList.map { it.SubStatus }
                var isExpanded by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 6.dp)
                ) {
                    Text(
                        text = item.FieldName,
                        modifier = Modifier.padding(horizontal = 2.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (item.Validate == "YES") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface // Use Material Theme color
                    )
                    Spacer(modifier = Modifier.height(7.dp))

                    selectedOption1.let {
                        DropdownComponent1(
                            item = item,
                            options = options,
                            isExpanded = isExpanded,
                            onExpandedChanged = { isExpanded = !isExpanded },
                            onDismiss = { isExpanded = false }, // Handle dismissal
                            selectedText = selectedOption1,
                            onOptionSelected = { text, index ->
                                selectedOption1 = text
                                isExpanded = false
                            }
                        )
                    }
                }
            } else if (item.FieldName == "Status") {
                // Handle resetting "Sub Status" field value when "Status" field value changes
                if (selectedOption != item.FieldValue) {

                }

                Column(
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 6.dp)
                ) {
                    Text(
                        text = item.FieldName,
                        modifier = Modifier.padding(horizontal = 2.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (item.Validate == "YES") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface // Use Material Theme color
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    selectedOption.let {
                        DropdownComponent1(
                            item = item,
                            options = options,
                            isExpanded = isExpanded,
                            onExpandedChanged = { isExpanded = !isExpanded },
                            onDismiss = { isExpanded = false }, // Handle dismissal
                            selectedText = it,
                            onOptionSelected = { text, index ->
                                selectedOption = text
                                isExpanded = false
                                onDropDownValueChanged(text)
                                selectedOption1 = ""
                                Log.d("deepak", selectedOption1.toString())
                            }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 6.dp)
                ) {
                    Text(
                        text = item.FieldName,
                        modifier = Modifier.padding(horizontal = 2.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (item.Validate == "YES") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface // Use Material Theme color
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    selectedOption.let {
                        DropdownComponent1(
//                        selectedOption = selectedOption,
                            item = item,
                            options = options,
                            isExpanded = isExpanded,
                            onExpandedChanged = { isExpanded = !isExpanded },
                            onDismiss = { isExpanded = false }, // Handle dismissal
                            selectedText = it,
                            onOptionSelected = { text, index ->
                                selectedOption = text
                                isExpanded = false
//                                if (item.FieldName == "Status") { // Check if FieldName is "Status"
//                                    Log.d("deepak","inside if statemnt")
//                                    selectedOption1 = ""
//                                    onDropDownValueChanged(text) // Execute only when FieldName is "Status"
//                                    Log.d("deepak","inside if statemnt 12343444")
//                                }
                            }
                        )
                    }
                }
            }
        }

        "FileUpload" -> {

            val uri1 = rememberSaveable { mutableStateOf<Uri?>(null) }
            val onSetUri: (Uri) -> Unit = { it ->
                uri1.value = it
            }
            val uri = uri1.value
            val context = LocalContext.current
            val cacheDir = context.cacheDir
            val directory = File(cacheDir, "images")
            val tempUri = remember { mutableStateOf<Uri?>(null) }
            val authority = stringResource(id = R.string.fileprovider)
            fun getTempUri(): Uri? {
                directory.let {
                    it.mkdirs()
                    val file = File.createTempFile(
                        "image_" + System.currentTimeMillis().toString(),
                        ".jpg",
                        it
                    )

                    return FileProvider.getUriForFile(
                        context,
                        authority,
                        file
                    )
                }
                return null
            }

            val imagePicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = {
                    it?.let {
                        onSetUri.invoke(it)
                    }
                }
            )

            val takePhotoLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture(),
                onResult = { isSaved ->
                    tempUri.value?.let {
                        onSetUri.invoke(it)
                    }
                }
            )

            val cameraPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted, proceed to step 2
                    val tmpUri = getTempUri()
                    tempUri.value = tmpUri
                    tempUri.value?.let {
                        takePhotoLauncher.launch(it)
                    }

                } else {
                    // Permission is denied, handle it accordingly
                }
            }

            var showBottomSheet by remember { mutableStateOf(false) }

            if (showBottomSheet) {
                MyModalBottomSheet(
                    onDismiss = {
                        showBottomSheet = false
                    },
                    onTakePhotoClick = {
                        showBottomSheet = false

                        val permission = Manifest.permission.CAMERA
                        if (ContextCompat.checkSelfPermission(
                                context,
                                permission
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            // Permission is already granted, proceed to step 2
                            val tmpUri = getTempUri()
                            tempUri.value = tmpUri
                            tempUri.value?.let {
                                takePhotoLauncher.launch(it)
                            }

                        } else {
                            // Permission is not granted, request it
                            cameraPermissionLauncher.launch(permission)
                        }
                    },
                    onPhotoGalleryClick = {
                        showBottomSheet = false
                        imagePicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            showBottomSheet = true
                        }, modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = item.FieldName)
                    }
                }

                //preview selfie
                uri?.let {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = it,
                            modifier = Modifier.size(
                                160.dp
                            ),
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // uploading on servere
//            Button(
//                onClick = {
//                    upload.invoke(it)
//                }
//            ) {
//                Text(text = "upload to server")
//            }
                }
            }
        }
    }
}