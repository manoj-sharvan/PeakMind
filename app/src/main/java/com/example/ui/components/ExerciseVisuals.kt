package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ExerciseVisualType
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.OrangeTertiary
import com.example.ui.theme.TealSecondary

@Composable
fun ExerciseIllustration(
    visualType: ExerciseVisualType,
    modifier: Modifier = Modifier,
    height: Dp = 190.dp
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val primaryColor = LimePrimary
                val secondaryColor = TealSecondary
                val accentOrange = OrangeTertiary
                val neutralColor = Color(0xFF94A3B8)

                // Background subtle athletic grid / floor plane
                drawFloorGrid()

                when (visualType) {
                    ExerciseVisualType.SQUAT -> drawSquatDualDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.PUSH_UP -> drawPushUpDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.GLUTE_BRIDGE -> drawGluteBridgeDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.LUNGE -> drawLungeDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.PLANK -> drawPlankDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.BIRD_DOG -> drawBirdDogDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.DEAD_BUG -> drawDeadBugDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.CAT_COW -> drawCatCowDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.CHILDS_POSE -> drawChildsPoseDemonstration(primaryColor, secondaryColor)
                    ExerciseVisualType.DIAPHRAGMATIC_BREATHING,
                    ExerciseVisualType.KEGEL_BASIC,
                    ExerciseVisualType.KEGEL_QUICK,
                    ExerciseVisualType.KEGEL_RELAXATION,
                    ExerciseVisualType.PELVIC_ANATOMY -> drawPelvicFloorAnatomy(primaryColor, secondaryColor, accentOrange)
                    else -> drawGenericFitnessDemonstration(primaryColor, secondaryColor)
                }
            }
        }
    }
}

/**
 * Step-by-Step Movement Breakdown (Start -> Lower -> Stand) as shown on the wireframe board
 */
@Composable
fun ExerciseStepByStepBar(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "B. Step-by-Step Movement",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StepThumbnail(stepNumber = "1", label = "Start")
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(16.dp)
                )
                StepThumbnail(stepNumber = "2", label = "Lower", isHighlighted = true)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(16.dp)
                )
                StepThumbnail(stepNumber = "3", label = "Stand")
            }
        }
    }
}

@Composable
private fun StepThumbnail(
    stepNumber: String,
    label: String,
    isHighlighted: Boolean = false
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(width = 72.dp, height = 54.dp)
                .background(
                    if (isHighlighted) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = if (isHighlighted) 1.5.dp else 1.dp,
                    color = if (isHighlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(36.dp)) {
                val color = if (isHighlighted) LimePrimary else Color(0xFF94A3B8)
                // Miniature figure representation
                drawCircle(color = color, radius = 4.dp.toPx(), center = Offset(size.width * 0.5f, 7.dp.toPx()))
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.5f, 11.dp.toPx()),
                    end = Offset(size.width * 0.5f, 22.dp.toPx()),
                    strokeWidth = 2.5.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.5f, 22.dp.toPx()),
                    end = Offset(size.width * 0.35f, 32.dp.toPx()),
                    strokeWidth = 2.5.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = color,
                    start = Offset(size.width * 0.5f, 22.dp.toPx()),
                    end = Offset(size.width * 0.65f, 32.dp.toPx()),
                    strokeWidth = 2.5.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$stepNumber. $label",
            style = MaterialTheme.typography.labelSmall,
            color = if (isHighlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.Normal
        )
    }
}

/**
 * Canvas drawing helpers for accurate exercise postures
 */
private fun DrawScope.drawFloorGrid() {
    val floorY = size.height * 0.82f
    drawLine(
        color = Color(0x33475569),
        start = Offset(20f, floorY),
        end = Offset(size.width - 20f, floorY),
        strokeWidth = 2f
    )
    for (i in 0..6) {
        val x = size.width * (0.15f + i * 0.12f)
        drawLine(
            color = Color(0x1A475569),
            start = Offset(x, floorY),
            end = Offset(x - 20f, floorY + 18f),
            strokeWidth = 1.5f
        )
    }
}

// 1. SQUAT DUAL DEMO (Standing Start + Deep Squat)
private fun DrawScope.drawSquatDualDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f

    // LEFT: Standing Figure (Start)
    val leftCenterX = size.width * 0.30f
    // Head
    drawCircle(color = secondaryColor, radius = 9.dp.toPx(), center = Offset(leftCenterX, size.height * 0.22f))
    // Torso
    drawLine(
        color = secondaryColor,
        start = Offset(leftCenterX, size.height * 0.28f),
        end = Offset(leftCenterX, size.height * 0.52f),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Arms forward for balance
    drawLine(
        color = secondaryColor,
        start = Offset(leftCenterX, size.height * 0.32f),
        end = Offset(leftCenterX + 28.dp.toPx(), size.height * 0.35f),
        strokeWidth = 4.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Legs (Standing upright)
    drawLine(
        color = secondaryColor,
        start = Offset(leftCenterX, size.height * 0.52f),
        end = Offset(leftCenterX - 8.dp.toPx(), floorY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = secondaryColor,
        start = Offset(leftCenterX, size.height * 0.52f),
        end = Offset(leftCenterX + 8.dp.toPx(), floorY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )

    // Motion indicator arrow
    drawArrow(
        start = Offset(size.width * 0.44f, size.height * 0.46f),
        end = Offset(size.width * 0.54f, size.height * 0.46f),
        color = Color(0xFF64748B)
    )

    // RIGHT: Squatting Figure (Action)
    val rightCenterX = size.width * 0.70f
    // Head
    drawCircle(color = primaryColor, radius = 9.dp.toPx(), center = Offset(rightCenterX - 14.dp.toPx(), size.height * 0.32f))
    // Angled Torso
    drawLine(
        color = primaryColor,
        start = Offset(rightCenterX - 14.dp.toPx(), size.height * 0.38f),
        end = Offset(rightCenterX - 28.dp.toPx(), size.height * 0.56f),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Reaching Arms
    drawLine(
        color = primaryColor,
        start = Offset(rightCenterX - 14.dp.toPx(), size.height * 0.40f),
        end = Offset(rightCenterX + 18.dp.toPx(), size.height * 0.38f),
        strokeWidth = 4.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Thigh parallel to ground
    drawLine(
        color = primaryColor,
        start = Offset(rightCenterX - 28.dp.toPx(), size.height * 0.56f),
        end = Offset(rightCenterX + 10.dp.toPx(), size.height * 0.57f),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Shin to floor
    drawLine(
        color = primaryColor,
        start = Offset(rightCenterX + 10.dp.toPx(), size.height * 0.57f),
        end = Offset(rightCenterX + 12.dp.toPx(), floorY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )

    // Kinetic target muscle glow (quads & glutes)
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(primaryColor.copy(alpha = 0.45f), Color.Transparent),
            center = Offset(rightCenterX - 10.dp.toPx(), size.height * 0.56f),
            radius = 24.dp.toPx()
        ),
        radius = 24.dp.toPx(),
        center = Offset(rightCenterX - 10.dp.toPx(), size.height * 0.56f)
    )
}

// 2. PUSH-UP DEMO
private fun DrawScope.drawPushUpDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f
    val startX = size.width * 0.18f
    val endX = size.width * 0.78f

    // Head
    drawCircle(color = primaryColor, radius = 8.dp.toPx(), center = Offset(endX + 16.dp.toPx(), size.height * 0.50f))
    // Rigid Torso & Legs line
    drawLine(
        color = primaryColor,
        start = Offset(startX, floorY), // Toes on floor
        end = Offset(endX, size.height * 0.53f), // Shoulders
        strokeWidth = 5.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Arms bent (Pushing up)
    val elbowX = endX - 10.dp.toPx()
    val elbowY = size.height * 0.66f
    drawLine(
        color = secondaryColor,
        start = Offset(endX, size.height * 0.53f),
        end = Offset(elbowX, elbowY),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = secondaryColor,
        start = Offset(elbowX, elbowY),
        end = Offset(elbowX + 4.dp.toPx(), floorY), // Palms on floor
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )

    // Chest & core engagement glow
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(primaryColor.copy(alpha = 0.4f), Color.Transparent),
            center = Offset(endX - 25.dp.toPx(), size.height * 0.57f),
            radius = 26.dp.toPx()
        ),
        radius = 26.dp.toPx(),
        center = Offset(endX - 25.dp.toPx(), size.height * 0.57f)
    )
}

// 3. GLUTE BRIDGE DEMO
private fun DrawScope.drawGluteBridgeDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f
    val headX = size.width * 0.22f

    // Head resting on floor
    drawCircle(color = secondaryColor, radius = 8.dp.toPx(), center = Offset(headX, floorY - 8.dp.toPx()))
    // Upper back on floor to elevated pelvis
    val hipX = size.width * 0.54f
    val hipY = size.height * 0.50f
    drawLine(
        color = primaryColor,
        start = Offset(headX + 12.dp.toPx(), floorY - 5.dp.toPx()),
        end = Offset(hipX, hipY),
        strokeWidth = 5.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Thigh from hip down to knee
    val kneeX = size.width * 0.72f
    val kneeY = size.height * 0.54f
    drawLine(
        color = primaryColor,
        start = Offset(hipX, hipY),
        end = Offset(kneeX, kneeY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Shin straight to floor
    drawLine(
        color = secondaryColor,
        start = Offset(kneeX, kneeY),
        end = Offset(kneeX - 4.dp.toPx(), floorY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Arms on floor
    drawLine(
        color = secondaryColor,
        start = Offset(headX + 18.dp.toPx(), floorY - 2.dp.toPx()),
        end = Offset(hipX - 10.dp.toPx(), floorY - 2.dp.toPx()),
        strokeWidth = 3.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Hip elevation upward arrow
    drawArrow(
        start = Offset(hipX, size.height * 0.68f),
        end = Offset(hipX, size.height * 0.55f),
        color = LimePrimary
    )
}

// 4. LUNGE DEMO
private fun DrawScope.drawLungeDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f
    val centerX = size.width * 0.50f

    // Head
    drawCircle(color = secondaryColor, radius = 8.dp.toPx(), center = Offset(centerX, size.height * 0.26f))
    // Torso upright
    drawLine(
        color = secondaryColor,
        start = Offset(centerX, size.height * 0.32f),
        end = Offset(centerX, size.height * 0.52f),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Front bent leg (90 degrees)
    val frontKneeX = centerX + 26.dp.toPx()
    val frontKneeY = size.height * 0.62f
    drawLine(
        color = primaryColor,
        start = Offset(centerX, size.height * 0.52f),
        end = Offset(frontKneeX, frontKneeY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = primaryColor,
        start = Offset(frontKneeX, frontKneeY),
        end = Offset(frontKneeX, floorY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Back leg stepping back
    val rearKneeX = centerX - 24.dp.toPx()
    val rearKneeY = floorY - 10.dp.toPx()
    drawLine(
        color = secondaryColor,
        start = Offset(centerX, size.height * 0.52f),
        end = Offset(rearKneeX, rearKneeY),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = secondaryColor,
        start = Offset(rearKneeX, rearKneeY),
        end = Offset(rearKneeX - 16.dp.toPx(), floorY),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
}

// 5. PLANK DEMO
private fun DrawScope.drawPlankDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f
    val startX = size.width * 0.20f
    val endX = size.width * 0.74f

    // Head
    drawCircle(color = secondaryColor, radius = 8.dp.toPx(), center = Offset(endX + 14.dp.toPx(), size.height * 0.55f))
    // Perfectly straight plank body
    drawLine(
        color = primaryColor,
        start = Offset(startX, floorY - 4.dp.toPx()),
        end = Offset(endX, size.height * 0.58f),
        strokeWidth = 5.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Forearms supporting (90 degrees)
    val forearmX = endX - 6.dp.toPx()
    drawLine(
        color = secondaryColor,
        start = Offset(endX, size.height * 0.58f),
        end = Offset(forearmX, floorY),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = secondaryColor,
        start = Offset(forearmX, floorY),
        end = Offset(forearmX + 18.dp.toPx(), floorY),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
}

// 6. BIRD DOG DEMO
private fun DrawScope.drawBirdDogDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f
    val centerX = size.width * 0.50f

    // Horizontal Torso
    drawLine(
        color = secondaryColor,
        start = Offset(centerX - 24.dp.toPx(), size.height * 0.56f),
        end = Offset(centerX + 24.dp.toPx(), size.height * 0.56f),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Head
    drawCircle(color = secondaryColor, radius = 7.dp.toPx(), center = Offset(centerX + 32.dp.toPx(), size.height * 0.53f))
    // Extended Arm (Forward)
    drawLine(
        color = primaryColor,
        start = Offset(centerX + 24.dp.toPx(), size.height * 0.56f),
        end = Offset(size.width * 0.82f, size.height * 0.54f),
        strokeWidth = 4.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Extended Opposite Leg (Backward)
    drawLine(
        color = primaryColor,
        start = Offset(centerX - 24.dp.toPx(), size.height * 0.56f),
        end = Offset(size.width * 0.18f, size.height * 0.56f),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Supporting Arm to floor
    drawLine(
        color = secondaryColor,
        start = Offset(centerX + 14.dp.toPx(), size.height * 0.56f),
        end = Offset(centerX + 14.dp.toPx(), floorY),
        strokeWidth = 4.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Supporting Knee to floor
    drawLine(
        color = secondaryColor,
        start = Offset(centerX - 10.dp.toPx(), size.height * 0.56f),
        end = Offset(centerX - 10.dp.toPx(), floorY),
        strokeWidth = 4.dp.toPx(),
        cap = StrokeCap.Round
    )
}

// 7. DEAD BUG DEMO
private fun DrawScope.drawDeadBugDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.76f
    val centerX = size.width * 0.50f

    // Flat back on mat
    drawLine(
        color = secondaryColor,
        start = Offset(centerX - 35.dp.toPx(), floorY),
        end = Offset(centerX + 35.dp.toPx(), floorY),
        strokeWidth = 5.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Head
    drawCircle(color = secondaryColor, radius = 7.5.dp.toPx(), center = Offset(centerX - 42.dp.toPx(), floorY))
    // Arms extending upward/overhead
    drawLine(
        color = primaryColor,
        start = Offset(centerX - 15.dp.toPx(), floorY),
        end = Offset(centerX - 35.dp.toPx(), floorY - 32.dp.toPx()),
        strokeWidth = 4.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = secondaryColor,
        start = Offset(centerX - 15.dp.toPx(), floorY),
        end = Offset(centerX - 10.dp.toPx(), floorY - 35.dp.toPx()),
        strokeWidth = 4.dp.toPx(),
        cap = StrokeCap.Round
    )
    // Knees 90 degrees
    drawLine(
        color = secondaryColor,
        start = Offset(centerX + 20.dp.toPx(), floorY),
        end = Offset(centerX + 20.dp.toPx(), floorY - 26.dp.toPx()),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = primaryColor,
        start = Offset(centerX + 20.dp.toPx(), floorY),
        end = Offset(centerX + 48.dp.toPx(), floorY - 14.dp.toPx()),
        strokeWidth = 4.5.dp.toPx(),
        cap = StrokeCap.Round
    )
}

// 8. CAT-COW DEMO
private fun DrawScope.drawCatCowDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f
    val centerX = size.width * 0.50f

    // Curved spine (Cow / Cat wave)
    val path = Path().apply {
        moveTo(centerX - 35.dp.toPx(), size.height * 0.58f)
        cubicTo(
            centerX - 15.dp.toPx(), size.height * 0.68f,
            centerX + 15.dp.toPx(), size.height * 0.68f,
            centerX + 35.dp.toPx(), size.height * 0.54f
        )
    }
    drawPath(path, color = primaryColor, style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round))
    // Head lifting
    drawCircle(color = secondaryColor, radius = 7.dp.toPx(), center = Offset(centerX + 44.dp.toPx(), size.height * 0.48f))
    // Supporting limbs
    drawLine(color = secondaryColor, start = Offset(centerX + 28.dp.toPx(), size.height * 0.56f), end = Offset(centerX + 28.dp.toPx(), floorY), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
    drawLine(color = secondaryColor, start = Offset(centerX - 28.dp.toPx(), size.height * 0.60f), end = Offset(centerX - 28.dp.toPx(), floorY), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
}

// 9. CHILD'S POSE DEMO
private fun DrawScope.drawChildsPoseDemonstration(primaryColor: Color, secondaryColor: Color) {
    val floorY = size.height * 0.82f
    val startX = size.width * 0.25f

    // Rounded hips resting on heels
    drawCircle(color = secondaryColor, radius = 10.dp.toPx(), center = Offset(startX, floorY - 12.dp.toPx()))
    // Torso folded along thighs to floor
    val path = Path().apply {
        moveTo(startX, floorY - 12.dp.toPx())
        cubicTo(
            startX + 20.dp.toPx(), floorY - 26.dp.toPx(),
            startX + 45.dp.toPx(), floorY - 10.dp.toPx(),
            startX + 65.dp.toPx(), floorY - 5.dp.toPx()
        )
    }
    drawPath(path, color = primaryColor, style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round))
    // Head on floor
    drawCircle(color = secondaryColor, radius = 6.5.dp.toPx(), center = Offset(startX + 62.dp.toPx(), floorY - 6.dp.toPx()))
    // Arms extended straight along floor
    drawLine(
        color = secondaryColor,
        start = Offset(startX + 50.dp.toPx(), floorY - 4.dp.toPx()),
        end = Offset(size.width * 0.78f, floorY - 2.dp.toPx()),
        strokeWidth = 3.5.dp.toPx(),
        cap = StrokeCap.Round
    )
}

// 10. PELVIC FLOOR ANATOMY DIAGRAM (Side-view pelvic sling, bladder, and rectum as in wireframe board)
private fun DrawScope.drawPelvicFloorAnatomy(primaryColor: Color, secondaryColor: Color, accentOrange: Color) {
    val centerX = size.width * 0.50f
    val centerY = size.height * 0.50f

    // Pelvic Bone Bowl contour (Pubic bone to Sacrum)
    val pelvicBowl = Path().apply {
        moveTo(centerX - 46.dp.toPx(), centerY - 16.dp.toPx())
        cubicTo(
            centerX - 35.dp.toPx(), centerY + 32.dp.toPx(),
            centerX + 35.dp.toPx(), centerY + 32.dp.toPx(),
            centerX + 46.dp.toPx(), centerY - 16.dp.toPx()
        )
    }
    drawPath(pelvicBowl, color = Color(0xFF64748B), style = Stroke(width = 4.dp.toPx()))

    // Pelvic Floor Sling (Hammock / Muscular sling in Electric Lime)
    val pelvicSling = Path().apply {
        moveTo(centerX - 38.dp.toPx(), centerY + 5.dp.toPx())
        cubicTo(
            centerX - 20.dp.toPx(), centerY + 28.dp.toPx(),
            centerX + 20.dp.toPx(), centerY + 28.dp.toPx(),
            centerX + 38.dp.toPx(), centerY + 5.dp.toPx()
        )
    }
    drawPath(pelvicSling, color = primaryColor, style = Stroke(width = 5.5.dp.toPx(), cap = StrokeCap.Round))

    // Bladder Organ (Teal)
    drawOval(
        color = secondaryColor.copy(alpha = 0.85f),
        topLeft = Offset(centerX - 24.dp.toPx(), centerY - 28.dp.toPx()),
        size = Size(28.dp.toPx(), 34.dp.toPx())
    )

    // Rectum / Sacral region (Warm Tint)
    drawOval(
        color = accentOrange.copy(alpha = 0.75f),
        topLeft = Offset(centerX + 8.dp.toPx(), centerY - 24.dp.toPx()),
        size = Size(20.dp.toPx(), 38.dp.toPx())
    )

    // Pulse/Biofeedback Wave Ring
    drawCircle(
        color = primaryColor.copy(alpha = 0.25f),
        radius = 38.dp.toPx(),
        center = Offset(centerX, centerY + 10.dp.toPx()),
        style = Stroke(width = 1.5.dp.toPx())
    )
}

private fun DrawScope.drawGenericFitnessDemonstration(primaryColor: Color, secondaryColor: Color) {
    val centerX = size.width * 0.5f
    val centerY = size.height * 0.5f
    drawCircle(color = primaryColor, radius = 28.dp.toPx(), center = Offset(centerX, centerY), style = Stroke(width = 3.dp.toPx()))
    drawLine(color = secondaryColor, start = Offset(centerX - 16.dp.toPx(), centerY), end = Offset(centerX + 16.dp.toPx(), centerY), strokeWidth = 4.dp.toPx(), cap = StrokeCap.Round)
}

private fun DrawScope.drawArrow(start: Offset, end: Offset, color: Color) {
    drawLine(color = color, start = start, end = end, strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
    val angle = kotlin.math.atan2(end.y - start.y, end.x - start.x)
    val arrowLen = 6.dp.toPx()
    val p1 = Offset(
        end.x - arrowLen * kotlin.math.cos(angle - 0.5).toFloat(),
        end.y - arrowLen * kotlin.math.sin(angle - 0.5).toFloat()
    )
    val p2 = Offset(
        end.x - arrowLen * kotlin.math.cos(angle + 0.5).toFloat(),
        end.y - arrowLen * kotlin.math.sin(angle + 0.5).toFloat()
    )
    drawLine(color = color, start = end, end = p1, strokeWidth = 2.dp.toPx())
    drawLine(color = color, start = end, end = p2, strokeWidth = 2.dp.toPx())
}
