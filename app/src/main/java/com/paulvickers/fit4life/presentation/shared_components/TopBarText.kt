package com.paulvickers.fit4life.presentation.shared_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulvickers.fit4life.ui.theme.F4LLightOrange
import com.paulvickers.fit4life.ui.theme.bungeeInlineFamily

@Composable
fun TopBarText(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(8.dp),
        text = text,
        color = F4LLightOrange,
        fontSize = 30.sp,
        fontFamily = bungeeInlineFamily,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1,
    )
}


@Preview
@Composable
fun TopBarTextPrev() {
    TopBarText(text = "Fit 4 Life")
}