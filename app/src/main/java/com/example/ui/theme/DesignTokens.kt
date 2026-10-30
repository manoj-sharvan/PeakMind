package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object PeakMindSpacing {
    val xxs = 4.dp
    val xs = 8.dp
    val sm = 12.dp
    val md = 16.dp
    val lg = 20.dp
    val xl = 24.dp
    val xxl = 32.dp

    val minTouchTarget = 48.dp
    val buttonHeight = 52.dp
    val buttonHeightSmall = 44.dp
    val inputHeight = 54.dp
}

object PeakMindShapes {
    val cardRadius = 20.dp
    val cardRadiusSmall = 16.dp
    val cardRadiusMini = 12.dp
    val buttonRadius = 26.dp
    val chipRadius = 20.dp

    val card = RoundedCornerShape(cardRadius)
    val cardSmall = RoundedCornerShape(cardRadiusSmall)
    val cardMini = RoundedCornerShape(cardRadiusMini)
    val button = RoundedCornerShape(buttonRadius)
    val chip = RoundedCornerShape(chipRadius)
}

object PeakMindGradients {
    val cardBackground = Brush.linearGradient(
        colors = listOf(
            Color(0xFF1B1F2A),
            Color(0xFF14171E)
        )
    )

    val heroWorkout = Brush.linearGradient(
        colors = listOf(
            Color(0xFF1E2638),
            Color(0xFF141822)
        )
    )

    val cyanGlowBorder = Brush.linearGradient(
        colors = listOf(
            Color(0x6606B6D4),
            Color(0x1A06B6D4),
            Color(0x33CCFF00)
        )
    )

    val limeGlowBorder = Brush.linearGradient(
        colors = listOf(
            Color(0x80CCFF00),
            Color(0x33CCFF00),
            Color(0x3306B6D4)
        )
    )

    val surfaceCardSubtle = Brush.linearGradient(
        colors = listOf(
            Color(0xFF222734),
            Color(0xFF191C24)
        )
    )
}
