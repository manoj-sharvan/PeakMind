package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PeakMindGradients
import com.example.ui.theme.PeakMindLime
import com.example.ui.theme.PeakMindLimeContainer
import com.example.ui.theme.PeakMindLimeOn
import com.example.ui.theme.PeakMindShapes
import com.example.ui.theme.PeakMindSpacing

/**
 * 1. PeakMindPrimaryButton
 * High-contrast Electric Lime button with subtle gradient, tactile spring press feedback,
 * 18-20dp rounded corners, and full dark/light mode accessibility (WCAG AAA compliant text contrast).
 */
@Composable
fun PeakMindPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(20.dp),
    testTag: String = "primary_button"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        animationSpec = spring(dampingRatio = 0.65f, stiffness = 400f),
        label = "btnScale"
    )

    val isDark = isSystemInDarkTheme() || MaterialTheme.colorScheme.background.luminance() < 0.5f

    // Electric lime subtle gradient
    val limeBrush = if (enabled) {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFD4FF26), // Lighter electric lime
                Color(0xFFCCFF00), // Pure electric lime
                Color(0xFFB8F000)  // Deep electric lime
            )
        )
    } else {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFCCFF00).copy(alpha = 0.35f),
                Color(0xFFB8F000).copy(alpha = 0.35f)
            )
        )
    }

    val contentColor = if (enabled) {
        Color(0xFF0F1115) // Deep charcoal dark text -> 13:1 contrast ratio against #CCFF00
    } else {
        Color(0xFF0F1115).copy(alpha = 0.45f)
    }

    val borderStroke = if (enabled && isDark) {
        BorderStroke(1.dp, Color(0x3306B6D4)) // Subtle cyan edge glow
    } else if (enabled) {
        BorderStroke(1.dp, Color(0x22000000))
    } else null

    Surface(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        interactionSource = interactionSource,
        border = borderStroke,
        color = Color.Transparent,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .scale(scale)
            .semantics {
                this.role = Role.Button
                this.contentDescription = text
            }
            .testTag(testTag)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(limeBrush),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

/**
 * 2. PeakMindSecondaryButton
 * Dark surface button with subtle cyan accent border and tactile press feedback.
 */
@Composable
fun PeakMindSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    testTag: String = "secondary_button"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 400f),
        label = "btnSecScale"
    )

    Surface(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = PeakMindShapes.button,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)),
        modifier = modifier
            .fillMaxWidth()
            .height(PeakMindSpacing.buttonHeight)
            .scale(scale)
            .testTag(testTag)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(PeakMindSpacing.xs))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

/**
 * 3. PeakMindWorkoutCard
 * Wireframe-styled hero workout card with 20dp rounded corners, subtle multi-stop gradients,
 * electric lime and cyan accents, tactile press animation, and full dark/light theme accessibility.
 */
@Composable
fun PeakMindWorkoutCard(
    title: String,
    duration: String,
    subtitle: String = "Today's Workout",
    onStartWorkout: () -> Unit,
    modifier: Modifier = Modifier,
    level: String? = null,
    thumbnailContent: (@Composable () -> Unit)? = null,
    testTag: String = "today_workout_card"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.985f else 1.0f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 350f),
        label = "workoutCardScale"
    )

    val isDark = isSystemInDarkTheme() || MaterialTheme.colorScheme.background.luminance() < 0.5f

    // Subtle multi-stop gradient for card surface
    val cardBackgroundBrush = if (isDark) {
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF1E2638), // Subtle navy/charcoal depth
                Color(0xFF161A25),
                Color(0xFF10131B)  // Seamless foundation blend
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                Color(0xFFFFFFFF),
                Color(0xFFF8FAFC),
                Color(0xFFF1F5F9)
            )
        )
    }

    // Cyan and electric lime glow border
    val cardBorderBrush = if (isDark) {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0x6606B6D4), // Cyan glow
                Color(0x33CCFF00), // Lime accent glow
                Color(0x2606B6D4)  // Cyan edge
            )
        )
    } else {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0x400284C7),
                Color(0x2665A30D),
                Color(0x260284C7)
            )
        )
    }

    val cardShape = RoundedCornerShape(20.dp)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClickLabel = "Start $title"
            ) { onStartWorkout() }
            .semantics {
                this.role = Role.Button
                this.contentDescription = "$subtitle: $title, Duration: $duration. Tap to start workout."
            }
            .testTag(testTag),
        shape = cardShape,
        color = Color.Transparent,
        border = BorderStroke(1.dp, cardBorderBrush),
        shadowElevation = if (isDark) 0.dp else 2.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardBackgroundBrush)
                .padding(20.dp)
        ) {
            Column {
                // Header row: Subtitle + Duration / Level Badges
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Electric Lime indicator pill
                        Box(
                            modifier = Modifier
                                .size(width = 8.dp, height = 8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFCCFF00))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Cyan duration pill badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isDark) Color(0x2606B6D4) else Color(0x1A0284C7)
                            )
                            .border(
                                width = 0.5.dp,
                                color = if (isDark) Color(0x4006B6D4) else Color(0x330284C7),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = if (isDark) Color(0xFF06B6D4) else Color(0xFF0284C7),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = duration,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isDark) Color(0xFF06B6D4) else Color(0xFF0284C7)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Title & optional Level
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (level != null) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = level,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Optional thumbnail or illustration slot
                    if (thumbnailContent != null) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    if (isDark) Color(0x331E293B) else Color(0x1A000000)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            thumbnailContent()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Integrated Primary Action Button with Electric Lime styling
                PeakMindPrimaryButton(
                    text = "Start Workout",
                    onClick = onStartWorkout,
                    leadingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                    modifier = Modifier.height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    testTag = "start_workout_inner_btn"
                )
            }
        }
    }
}

/**
 * 4. PeakMindExerciseCard
 * Reusable exercise item card with thumbnail container, metadata, and favorite heart toggle.
 */
@Composable
fun PeakMindExerciseCard(
    title: String,
    categoryAndLevel: String,
    stats: String,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    thumbnailContent: @Composable () -> Unit,
    testTag: String = "exercise_card"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.985f else 1.0f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 400f),
        label = "exCardScale"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(interactionSource = interactionSource, indication = null) { onClick() }
            .testTag(testTag),
        shape = PeakMindShapes.cardSmall,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PeakMindSpacing.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 80.dp, height = 70.dp)
                    .clip(PeakMindShapes.cardMini)
            ) {
                thumbnailContent()
            }

            Spacer(modifier = Modifier.width(PeakMindSpacing.md))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = categoryAndLevel,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stats,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            IconButton(
                onClick = onFavoriteToggle,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color(0xFFEF4444) else MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

/**
 * 5. PeakMindCategoryCard
 * Grid category card with distinct gradient depth and icon badge.
 */
@Composable
fun PeakMindCategoryCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    gradientColors: List<Color>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "category_card"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 400f),
        label = "catScale"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(132.dp)
            .scale(scale)
            .clickable(interactionSource = interactionSource, indication = null) { onClick() }
            .testTag(testTag),
        shape = PeakMindShapes.card,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(gradientColors))
                .padding(PeakMindSpacing.md)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(36.dp)
                    .background(Color.White.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFCBD5E1),
                    maxLines = 1
                )
            }
        }
    }
}

/**
 * 6. PeakMindMetricCard
 * Metric tile for weights, minutes, and workout totals.
 */
@Composable
fun PeakMindMetricCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    testTag: String = "metric_card"
) {
    Surface(
        modifier = modifier.testTag(testTag),
        shape = PeakMindShapes.cardSmall,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier.padding(PeakMindSpacing.md)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = valueColor
            )
        }
    }
}

/**
 * 7. PeakMindProgressBar
 * Animated smooth progress bar filling on appearance.
 */
@Composable
fun PeakMindProgressBar(
    targetProgress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = Color(0xFF262D38)
) {
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(targetProgress) {
        animatedProgress.animateTo(
            targetValue = targetProgress,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
    }

    LinearProgressIndicator(
        progress = { animatedProgress.value },
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
        color = color,
        trackColor = trackColor,
        strokeCap = StrokeCap.Round
    )
}

/**
 * 8. PeakMindSectionHeader
 * Typography-consistent header with optional right action.
 */
@Composable
fun PeakMindSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = PeakMindSpacing.xs),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (actionText != null && onActionClick != null) {
            Text(
                text = actionText,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable { onActionClick() }
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}

/**
 * 9. PeakMindFilterChip
 * Pill chip with active lime border, background tint, and smooth selection state.
 */
@Composable
fun PeakMindFilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "filter_chip"
) {
    val animatedBorderColor by animateColorAsState(
        targetValue = if (isSelected) PeakMindLime else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
        label = "chipBorder"
    )
    val animatedBgColor by animateColorAsState(
        targetValue = if (isSelected) PeakMindLimeContainer else MaterialTheme.colorScheme.surfaceVariant,
        label = "chipBg"
    )

    Surface(
        onClick = onClick,
        shape = PeakMindShapes.chip,
        color = animatedBgColor,
        border = BorderStroke(1.dp, animatedBorderColor),
        modifier = modifier
            .defaultMinSize(minHeight = 36.dp)
            .testTag(testTag)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) PeakMindLime else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * 10. PeakMindLevelCard
 * Stacked level selection card with radio indicator, volume metadata, and glowing lime border.
 */
@Composable
fun PeakMindLevelCard(
    title: String,
    subtitle: String,
    stats: String,
    icon: ImageVector,
    accentColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "level_card"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.985f else 1.0f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 400f),
        label = "levelScale"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(interactionSource = interactionSource, indication = null) { onClick() }
            .testTag(testTag),
        shape = PeakMindShapes.card,
        color = if (isSelected) PeakMindLimeContainer else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) PeakMindLime else MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PeakMindSpacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(accentColor.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(PeakMindSpacing.sm))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) PeakMindLime else MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stats,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isSelected) PeakMindLime.copy(alpha = 0.9f) else MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Icon(
                imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (isSelected) PeakMindLime else MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

/**
 * 11. PeakMindBottomNavigation
 * Bottom bar with WindowInsets padding and active electric lime pill indicator.
 */
@Composable
fun PeakMindBottomNavigation(
    currentRoute: String,
    onTabSelected: (String) -> Unit,
    tabs: List<BottomNavTabItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("peakmind_bottom_nav"),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        tabs.forEach { tab ->
            val isSelected = currentRoute == tab.route

            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab.route) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PeakMindLimeOn,
                    selectedTextColor = PeakMindLime,
                    indicatorColor = PeakMindLime,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.testTag("nav_tab_${tab.route}")
            )
        }
    }
}

data class BottomNavTabItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

/**
 * 12. PeakMindTimerCard
 * Circular rest/countdown gauge with smooth gradient arc and readable digital countdown.
 */
@Composable
fun PeakMindTimerCard(
    secondsRemaining: Int,
    totalSeconds: Int = 45,
    label: String = "RECOVER",
    modifier: Modifier = Modifier
) {
    val secondaryColor = MaterialTheme.colorScheme.secondary

    Box(
        modifier = modifier.size(210.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 10.dp.toPx()
            // Track ring
            drawCircle(
                color = Color(0xFF1E293B),
                style = Stroke(width = strokeWidth)
            )
            // Progress arc
            val sweepAngle = (secondsRemaining.toFloat() / totalSeconds.toFloat()) * 360f
            drawArc(
                brush = Brush.sweepGradient(
                    listOf(
                        secondaryColor,
                        PeakMindLime,
                        secondaryColor
                    )
                ),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val mins = secondsRemaining / 60
            val secs = secondsRemaining % 60
            val timeString = String.format("%02d:%02d", mins, secs)
            Text(
                text = timeString,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }
    }
}
