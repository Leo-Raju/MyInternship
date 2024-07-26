package com.example.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sampleapp.ui.theme.SampleAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.activity.viewModels

class MainActivity : ComponentActivity() {
    private val viewModel: DiaryViewModel by viewModels {
        DiaryViewModelFactory(repository = (application as DiaryApplication).repository, application = DiaryApplication() )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "entries") {
                        composable("entries") {
                            EntriesFragment(
                                navController,
                                LocalContext.current
                            )
                        }
                        composable("new entry") { NewEntryFragment(navController, viewModel) }
                        composable("entry/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")
                            id?.let { DiaryEntry(navController, viewModel, id = id.toInt()) }
                        }
                    }
                }
            }
        }
    }
}

