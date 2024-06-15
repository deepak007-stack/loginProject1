package com.example.loginproject.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownComponent(
    options: List<String>,
    isExpanded: Boolean,
    onExpandedChanged: () -> Unit,
    onDismiss: () -> Unit,
    selectedText: String,
    onOptionSelected: (String, Int) -> Unit,
) {

//    val focusRequester = remember { FocusRequester() }
//
//    LaunchedEffect(isExpanded) {
//        if (!isExpanded) {
//            focusRequester.requestFocus()
//        }
//    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { onExpandedChanged() },
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .fillMaxWidth()
    ) {
        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color.White, // Customize cursor color as needed
//            textColor = Color.White // Customize text color as needed
        )

        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            colors = textFieldColors,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                onDismiss()
//                focusRequester.requestFocus()
            }) {
            options.forEachIndexed { index, dropDownMenuItems ->
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(), text = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = dropDownMenuItems)
                    }

                }, onClick = {
                    onOptionSelected(options[index], index)
                },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
