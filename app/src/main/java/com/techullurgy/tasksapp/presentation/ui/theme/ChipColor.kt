package com.techullurgy.tasksapp.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorSpec(
    val container: Color,
    val content: Color
)

data class ChipColors(
    val default: ColorSpec,
    val gray: ColorSpec,
    val brown: ColorSpec,
    val orange: ColorSpec,
    val yellow: ColorSpec,
    val green: ColorSpec,
    val blue: ColorSpec,
    val purple: ColorSpec,
    val pink: ColorSpec,
    val red: ColorSpec
)

val LocalChipColors = staticCompositionLocalOf {
    lightChipColors
}

val lightChipColors = ChipColors(
    default = ColorSpec(
        container = Color(0xFF373530),
        content = Color(0xFFFFFFFF)
    ),
    gray = ColorSpec(
        container = Color(0xFF787774),
        content = Color(0xFFF1F1EF)
    ),
    brown = ColorSpec(
        container = Color(0xFF976D57),
        content = Color(0xFFF3EEEE)
    ),
    orange = ColorSpec(
        container = Color(0xFFCC782F),
        content = Color(0xFFF8ECDF)
    ),
    yellow = ColorSpec(
        container = Color(0xFFC29343),
        content = Color(0xFFFAF3DD)
    ),
    green = ColorSpec(
        container = Color(0xFF548164),
        content = Color(0xFFEEF3ED)
    ),
    blue = ColorSpec(
        container = Color(0xFF487CA5),
        content = Color(0xFFE9F3F7)
    ),
    purple = ColorSpec(
        container = Color(0xFF8A67AB),
        content = Color(0xFFF6F3F8)
    ),
    pink = ColorSpec(
        container = Color(0xFFB35488),
        content = Color(0xFFF9F2F5)
    ),
    red = ColorSpec(
        container = Color(0xFFC4554D),
        content = Color(0xFFFAECEC)
    ),
)

val darkChipColors = ChipColors(
    default = ColorSpec(
        container = Color(0xFFD4D4D4),
        content = Color(0xFF191919)
    ),
    gray = ColorSpec(
        container = Color(0xFF9B9B9B),
        content = Color(0xFF252525)
    ),
    brown = ColorSpec(
        container = Color(0xFFA27763),
        content = Color(0xFF2E2724)
    ),
    orange = ColorSpec(
        container = Color(0xFFCB7B37),
        content = Color(0xFF36291F)
    ),
    yellow = ColorSpec(
        container = Color(0xFFC19138),
        content = Color(0xFF372E20)
    ),
    green = ColorSpec(
        container = Color(0xFF4F9768),
        content = Color(0xFF242B26)
    ),
    blue = ColorSpec(
        container = Color(0xFF447ACB),
        content = Color(0xFF1F2A2D)
    ),
    purple = ColorSpec(
        container = Color(0xFF865DBB),
        content = Color(0xFF2A2430)
    ),
    pink = ColorSpec(
        container = Color(0xFFBA4A78),
        content = Color(0xFF2E2328)
    ),
    red = ColorSpec(
        container = Color(0xFFBE524B),
        content = Color(0xFF332523)
    ),
)