package com.paulvickers.fit4life.presentation.shared_components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

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
        title = { Text(text = "Please confirm") },
        text = { Text(text = "Are you sure you want to delete this $titleToDelete?") }
    )
}
