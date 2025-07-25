package com.hshamkhani.hotlinenews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.hshamkhani.hotlinenews.ui.theme.HotlinenewsTheme
import com.hshamkhani.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotlinenewsTheme {
                val navHostController = rememberNavController()
                AppNavHost(
                    navHostController = navHostController,
                )
            }
        }
    }
}
