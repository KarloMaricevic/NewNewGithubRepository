package com.example.githubapp.feature.login.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen() {
    Box(Modifier.fillMaxSize()) {
        Text(
            text = "Hello!",
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
