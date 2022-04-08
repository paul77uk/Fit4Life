package com.paulvickers.fit4life.presentation.shared_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulvickers.fit4life.ui.theme.F4LBlack
import com.paulvickers.fit4life.ui.theme.F4LLightOrange

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DestinationCard(
    text: String,
    clickable: () -> Unit = {},
    icon: @Composable () -> Unit = {},
    trailing: @Composable () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .clickable {
                clickable()
            },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = F4LLightOrange
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Text(
                text = text,
                modifier = Modifier.weight(0.8f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
            )
            trailing()
        }
    }
}

@Preview
@Composable
fun DestinationCardPrev() {
    DestinationCard(text = "Destination")
}

@Preview
@Composable
fun DestinationCardWithIconsPrev() {
    DestinationCard(
        text = "Destination", icon = {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Edit Icon",
                tint = F4LBlack
            )
        },
        trailing = {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = F4LBlack
            )
        }
    )
}