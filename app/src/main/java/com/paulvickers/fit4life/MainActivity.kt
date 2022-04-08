package com.paulvickers.fit4life

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.paulvickers.fit4life.presentation.NavGraphs
import com.paulvickers.fit4life.ui.theme.Fit4LifeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fit4LifeTheme {
                // A surface container using the 'background' color from the theme
               Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   DestinationsNavHost(navGraph = NavGraphs.root)
               }
            }
        }
    }
}
