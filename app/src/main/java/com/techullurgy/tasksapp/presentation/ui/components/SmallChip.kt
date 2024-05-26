package com.techullurgy.tasksapp.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.techullurgy.tasksapp.presentation.ui.theme.ColorSpec
import com.techullurgy.tasksapp.presentation.ui.theme.lightChipColors

@Composable
fun SmallChip(
    value: String,
    color: ColorSpec,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(color.container, cornerRadius = CornerRadius(80f))
            }
            .padding(horizontal = 8.dp, vertical = 1.dp)
    ) {
        Text(text = value, color = color.content, fontWeight = FontWeight.SemiBold)
    }
}

private data class SmallChipsParams(
    val value: String,
    val color: ColorSpec
)

private class SmallChipsParameterProvider : PreviewParameterProvider<SmallChipsParams> {
    override val values: Sequence<SmallChipsParams>
        get() = sequenceOf(
            SmallChipsParams(
                value = "New",
                color = lightChipColors.default,
            ),
            SmallChipsParams(
                value = "Completed",
                color = lightChipColors.red,
            ),
            SmallChipsParams(
                value = "New",
                color = lightChipColors.blue,
            ),
            SmallChipsParams(
                value = "New",
                color = lightChipColors.green,
            ),
            SmallChipsParams(
                value = "New",
                color = lightChipColors.yellow,
            ),
            SmallChipsParams(
                value = "New",
                color = lightChipColors.pink,
            ),
            SmallChipsParams(
                value = "Completed",
                color = lightChipColors.purple,
            ),
            SmallChipsParams(
                value = "Completed",
                color = lightChipColors.brown,
            ),
            SmallChipsParams(
                value = "Completed",
                color = lightChipColors.orange,
            ),
        )
}

@Preview
@Composable
private fun SmallChipsVariants(
    @PreviewParameter(SmallChipsParameterProvider::class) params: SmallChipsParams
) {
    SmallChip(value = params.value, color = params.color)
}