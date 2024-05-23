package com.techullurgy.tasksapp.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SmallChip(
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color, cornerRadius = CornerRadius(80f))
            }
            .padding(horizontal = 8.dp)
    ) {
        Text(text = value, color = Color.White, fontWeight = FontWeight.Medium)
    }
}