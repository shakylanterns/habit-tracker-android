package edu.cuhk.csci3310

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.cuhk.csci3310.ui.nav.BottomNavBar
import edu.cuhk.csci3310.ui.nav.NavHostScreens
import edu.cuhk.csci3310.ui.theme.HabitTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HabitTrackerTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    },
                ) {
                        paddingValues ->
                    Box(
                        modifier =
                            Modifier
                                .padding(paddingValues)
                                .fillMaxSize(),
                    ) {
                        NavHostScreens(navController = navController)
                    }
                }
            }
        }
    }
}
