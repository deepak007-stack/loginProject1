package com.example.loginproject.screens.myDataList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginproject.component.DropdownComponent
import com.example.loginproject.model.HomeData

@Composable
fun MyList(
    data: List<HomeData>,
    paddingValues: PaddingValues,
    navController: NavHostController
) {

    val processNameList = mutableListOf<String>()
    var isExpanded by remember { mutableStateOf(false) }
    var selectedProcessName by remember { mutableStateOf("Select Process") }

    val filteredData = if (selectedProcessName == "ALL") {
        data
    } else {
        data.filter { it.ProcessName == selectedProcessName }
    }

    processNameList.add("ALL")
    data.map { it.ProcessName }.distinct().forEach {
        processNameList.add(it)
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        ) {
            DropdownComponent(
                options = processNameList,
                isExpanded = isExpanded,
                onExpandedChanged = { isExpanded = !isExpanded },
                onDismiss = { isExpanded = false },
                selectedText = selectedProcessName,
                onOptionSelected = { text, index ->
                    selectedProcessName = text
//                    selectedText2 = names[index]
//                    selectedText3 = fieldValues[index]
                    isExpanded = false
                }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            itemsIndexed(items = filteredData) { index, item ->
                ShowItem(item, navController)
            }
        }
    }
}

@Composable
fun ShowItem(item: HomeData, navController: NavHostController) {

    val caseTypeSubstring = item.CaseType?.substringBefore(',') ?: ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                navController.navigate("details_screen/${item.nid}/${item.ProcessID}/${caseTypeSubstring}")
            },
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(5.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "ProcessName :  ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${item.ProcessName.toString()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "AccountID :  ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${item.AccountID.toString()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }

            Spacer(modifier = Modifier.height(3.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Customer_Name :  ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${item.Customer_Name.toString()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "CaseType :  ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${item.CaseType.toString()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(3.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Ageing :  ",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${item.Ageing.toString()}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "Residance_Address :  ",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${item.Residance_Address.toString()}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

}