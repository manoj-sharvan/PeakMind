package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.TealSecondary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        delay(2200)
        onNavigateNext()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F172A),
                        DarkBackground,
                        Color(0xFF07090C)
                    )
                )
            )
            .clickable { onNavigateNext() }
            .testTag("splash_screen"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            // PeakMind Geometric Peak Emblem
            Canvas(modifier = Modifier.size(110.dp)) {
                val cx = size.width / 2
                val cy = size.height / 2

                // Outer Peak (Electric Lime)
                val outerPeak = Path().apply {
                    moveTo(cx, cy - 40.dp.toPx())
                    lineTo(cx + 42.dp.toPx(), cy + 30.dp.toPx())
                    lineTo(cx + 26.dp.toPx(), cy + 30.dp.toPx())
                    lineTo(cx, cy - 14.dp.toPx())
                    lineTo(cx - 26.dp.toPx(), cy + 30.dp.toPx())
                    lineTo(cx - 42.dp.toPx(), cy + 30.dp.toPx())
                    close()
                }
                drawPath(outerPeak, color = LimePrimary)

                // Inner Peak (Teal / Cyan)
                val innerPeak = Path().apply {
                    moveTo(cx, cy - 2.dp.toPx())
                    lineTo(cx + 18.dp.toPx(), cy + 30.dp.toPx())
                    lineTo(cx - 18.dp.toPx(), cy + 30.dp.toPx())
                    close()
                }
                drawPath(innerPeak, color = TealSecondary)

                // Ambient glow
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(LimePrimary.copy(alpha = 0.25f), Color.Transparent),
                        center = Offset(cx, cy),
                        radius = 60.dp.toPx()
                    ),
                    radius = 60.dp.toPx(),
                    center = Offset(cx, cy)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "PeakMind",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Stronger Body\nHealthier Mind\nBetter You",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF94A3B8),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
        }

        // Bottom Loading Indicator
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = LimePrimary,
                strokeWidth = 3.dp,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B)
            )
        }
    }
}
