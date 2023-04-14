package com.example.notes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notes.di.AppModule
import com.example.notes.presentation.app.AppViewModel
import com.example.notes.presentation.components.*
import com.example.notes.presentation.screens.AccountScreen
import com.example.notes.presentation.screens.LoginScreen
import com.example.notes.presentation.screens.SignupScreen
import com.example.notes.presentation.util.Screen
import com.example.notes.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// Singleton which represents muteable state across the application, should be injected
@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    @Inject
    lateinit var appViewModel: AppViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(
                                navController = navController,
                                appViewModel = appViewModel
                            )
                        }
                        composable(route = Screen.AccountScreen.route) {
                            AccountScreen(
                                navController = navController,
                                appViewModel = appViewModel
                            )
                        }
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(
                                navController = navController,
                                appViewModel = appViewModel
                            )
                        }
                        composable(route = Screen.SignupScreen.route) {
                            SignupScreen(
                                navController = navController,
                                appViewModel = appViewModel
                            )
                        }
                        composable(
                            route = Screen.AddEditScreen.route +
                                    "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.LongType
                                    defaultValue = 0
                                }
                            )
                        ) {
                            //val id = it.arguments?.getLong("noteId") ?: null
                            AddEditScreen(
                                navController = navController,
                                appViewModel = appViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}