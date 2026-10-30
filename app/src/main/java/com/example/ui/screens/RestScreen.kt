package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ExerciseVisualType
import com.example.ui.components.ExerciseIllustration
import com.example.ui.components.PeakMindTopBar
import com.example.ui.components.PrimaryLimeButton
import com.example.ui.components.SecondaryDarkButton
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.TealSecondary
import kotlinx.coroutines.delay

@Composable
fun RestScreen(
    nextExerciseName: String = "Push Up",
    nextExerciseVisual: ExerciseVisualType = ExerciseVisualType.PUSH_UP,
    nextExerciseDetail: String = "3 sets • 10 reps",
    onFinishRest: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var secondsRemaining by remember { mutableIntStateOf(45) }
    val totalSeconds = 45

    LaunchedEffect(Unit) {
        while (secondsRemaining > 0) {
            delay(1000)
            secondsRemaining--
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("rest_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PeakMindTopBar(
            title = "Rest",
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Circular Timer Gauge
        com.example.ui.components.PeakMindTimerCard(
            secondsRemaining = secondsRemaining,
            totalSeconds = totalSeconds,
            label = "RECOVER"
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Next Exercise Preview Card
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Next Exercise",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = nextExerciseName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Mini Illustration
                ExerciseIllustration(
                    visualType = nextExerciseVisual,
                    height = 95.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = nextExerciseDetail,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TealSecondary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Action Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryDarkButton(
                text = "Skip Rest",
                onClick = onFinishRest,
                modifier = Modifier.weight(1f),
                testTag = "skip_rest_button"
            )
            PrimaryLimeButton(
                text = "Resume",
                onClick = onFinishRest,
                modifier = Modifier.weight(1f),
                testTag = "resume_rest_button"
            )
        }
    }
}
