package com.example.loginproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.loginproject.model.Data

@Composable
fun MyList(data: List<Data>, paddingValues: PaddingValues) {

    val processNameList = mutableListOf<String>()
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("ALL") }

    data.map { it.ProcessName }.distinct().forEach {
        processNameList.add(it)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            DropdownComponent(
                options = processNameList,
                isExpanded = isExpanded,
                onExpandedChanged = { isExpanded = !isExpanded },
                onDismiss = { isExpanded = false },
                selectedText = selectedText,
                onOptionSelected = { text, index ->
                    selectedText = text
//                    selectedText2 = names[index]
//                    selectedText3 = fieldValues[index]
                    isExpanded = false
                }
            )
        }
    }


//    LazyColumn(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(paddingValues)
//    ) {
//        itemsIndexed(items = data) { index, item ->
//            ShowItem(item.toString())
//        }
//    }
}

@Composable
fun ShowItem(item: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
//            Text(text = item, style = MaterialTheme.typography.titleMedium)
//            Text(
//                text = "Description for Item : $item ",
//                style = MaterialTheme.typography.bodyLarge
//            )

        }
    }

}