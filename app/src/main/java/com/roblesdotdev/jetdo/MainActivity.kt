package com.roblesdotdev.jetdo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetDoTheme {
                Text(text = "Working")
            }
        }
    }
}
