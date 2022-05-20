package com.paulvickers.fit4life.presentation.shared_components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.paulvickers.fit4life.ui.theme.F4LDarkGrey
import com.paulvickers.fit4life.ui.theme.F4LLightOrange

@Composable
fun F4LEditDialog(
    dismiss: () -> Unit,
    save: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardType: KeyboardType
) {
    AlertDialog(
        onDismissRequest = dismiss,
        confirmButton = {
            TextButton(onClick = save)
            { Text(text = "Save") }
        },
        dismissButton = {
            TextButton(onClick = dismiss)
            { Text(text = "Cancel") }
        },
        title = { Text(text = text, color = F4LLightOrange) },
        text = {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { save() }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = F4LDarkGrey,
                    textColor = F4LLightOrange
                )
            )
        },
        shape = RoundedCornerShape(12)
    )
}
