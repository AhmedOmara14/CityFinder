package com.omaradev.cityfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.omaradev.cityfinder.presentation.all_cities.AllCitiesScreen
import com.omaradev.cityfinder.ui.theme.CityFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityFinderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AllCitiesScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}