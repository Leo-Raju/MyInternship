package com.example.sampleapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext


@Composable
fun DiaryEntry(navController: NavController, viewModel: DiaryViewModel, id: Int) {

    val entries by viewModel.getEntriesById(id).observeAsState(emptyList())
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),
            verticalArrangement = Arrangement.Top) {
            entries.forEach { diary ->
                var entry by remember { mutableStateOf(diary.content) }
                OutlinedTextField(
                    value = entry,
                    onValueChange = { entry = it },
                    label = { Text("Entry $id") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    val location = viewModel.getCoordinates(context)
                    viewModel.update(Diary(id = diary.id, content = entry, time = System.currentTimeMillis(), latitude = location?.latitude?:0.0, longitude = location?.longitude?:0.0))
                    navController.navigate("entries")
                }, modifier = Modifier.align(Alignment.CenterHorizontally))
                {
                    Text(text = "Edit")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    viewModel.delete(diary)
                    navController.navigate("entries")
                }, modifier = Modifier.align(Alignment.CenterHorizontally))
                {
                    Text(text = "Delete")
                }
            }
        }
    }
}


