package com.techullurgy.tasksapp.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun KanbanIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit
) {
    Canvas(
        modifier = modifier
            .size(size)
            .clickable(onClick = onClick)
    ) {
        // Border
        drawRoundRect(
            color = tint,
            cornerRadius = CornerRadius(x = 5.dp.toPx()),
            style = Stroke(width = 2.dp.toPx())
        )

        val gapPercentage = .1f
        val gap = this.size.width * gapPercentage
        val barSize = (this.size.width - (4 * gap)) / 3f

        drawRoundRect(
            color = tint,
            topLeft = Offset(gap, this.size.height * gapPercentage),
            size = Size(barSize, this.size.height * .75f),
            cornerRadius = CornerRadius(barSize * .45f)
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(2 * gap + barSize, this.size.height * gapPercentage),
            size = Size(barSize, this.size.height * .5f),
            cornerRadius = CornerRadius(barSize * .45f)
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(3 * gap + 2 * barSize, this.size.height * gapPercentage),
            size = Size(barSize, this.size.height * .65f),
            cornerRadius = CornerRadius(barSize * .45f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KanbanIconPreview() {
    Box(modifier = Modifier
        .background(Color.Blue)
        .padding(16.dp)) {
        KanbanIcon { }
    }
}