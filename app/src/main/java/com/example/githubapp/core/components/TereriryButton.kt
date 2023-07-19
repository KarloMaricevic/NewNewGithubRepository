package com.example.githubapp.core.components

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TertiaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
    ) { Text(text) }
}

@Preview
@Composable
fun TertiaryButtonPreview() {
    TertiaryButton(
        text = "BUTTON",
        {}
    )
}