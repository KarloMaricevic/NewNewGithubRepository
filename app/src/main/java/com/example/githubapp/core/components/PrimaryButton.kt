package com.example.githubapp.core.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) { Text(text = text) }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "BUTTON",
        {}
    )
}