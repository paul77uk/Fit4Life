package com.paulvickers.fit4life.presentation.shared_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulvickers.fit4life.ui.theme.F4LBlack
import com.paulvickers.fit4life.ui.theme.F4LLightOrange

@Composable
fun F4LButton(
    text: String = "",
    onClick: () -> Unit = {},
    leftIcon: @Composable () -> Unit = {},
    rightIcon: @Composable () -> Unit = {}
) {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.padding(5.dp),
            elevation = ButtonDefaults.elevation(5.dp),
            border = BorderStroke(1.dp, F4LLightOrange),
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = F4LLightOrange,
                backgroundColor = F4LBlack
            ),
        ) {
            leftIcon()
            Text(text = text, fontSize = 18.sp)
            rightIcon()
        }
}

@Preview
@Composable
fun F4LButtonPrev() {
    F4LButton(text = "Text")
}