package com.example.sampleapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.flow.first


@Composable
fun EntriesFragment(navController: NavController, context: Context) {
    val db = DiaryRoomDatabase.getInstance(context)
    val diaryDao = db.diaryDao()

    var diary by remember { mutableStateOf(listOf<Diary>()) }

    LaunchedEffect(Unit) {
        diary = diaryDao.getAllEntries().first()
    }

    Box(modifier = Modifier.fillMaxSize()) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(diary) { diary ->
            Text(
                text = "Entry ${diary.id}",
                modifier = Modifier
                    .clickable {
                        navController.navigate("entry/${diary.id}")
                }
                    .padding(16.dp)

            )
        }
        }
        ExtendedFloatingActionButton(
                        onClick = { navController.navigate("new entry")},
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
            text = { Text(text = "New Entry") }
        )
        }
    }