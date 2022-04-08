package com.paulvickers.fit4life.presentation.shared_components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.paulvickers.fit4life.ui.theme.F4LLightOrange

@Composable
fun DialogWindow(dismiss: () -> Unit, delete: () -> Unit, titleToDelete: String) {
    AlertDialog(
        onDismissRequest = dismiss,
        confirmButton = {
            TextButton(onClick = delete)
            { Text(text = "Delete") }
        },
        dismissButton = {
            TextButton(onClick = dismiss)
            { Text(text = "Cancel") }
        },
        title = { Text(text = "Please confirm", color = F4LLightOrange) },
        text = { Text(text = "Are you sure you want to delete this $titleToDelete?") },
        shape = RoundedCornerShape(12)
    )
}