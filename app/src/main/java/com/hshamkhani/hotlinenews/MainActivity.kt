package com.hshamkhani.hotlinenews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.hshamkhani.designsystem.theme.HotlineNewsTheme
import com.hshamkhani.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            HotlineNewsTheme {
                AppNavHost(
                    navHostController = navHostController,
                )
            }
        }
    }
}
