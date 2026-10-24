package com.darien.vpweek5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.darien.vpweek5.Soal1.ui.theme.VpWeek5Theme
import com.darien.vpweek5.Soal2.ui.Screen.StateScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VpWeek5Theme {
                StateScreen()
            }
        }
    }
}