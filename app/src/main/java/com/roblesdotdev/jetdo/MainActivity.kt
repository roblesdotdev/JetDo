package com.roblesdotdev.jetdo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.roblesdotdev.jetdo.core.ui.theme.JetDoTheme
import com.roblesdotdev.jetdo.login.ui.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetDoTheme {
                val localContext = LocalContext.current.applicationContext
                LoginScreen(onLoginComplete = {
                    Toast.makeText(
                        localContext,
                        "Login Success",
                        Toast.LENGTH_LONG
                    ).show()
                })
            }
        }
    }
}
