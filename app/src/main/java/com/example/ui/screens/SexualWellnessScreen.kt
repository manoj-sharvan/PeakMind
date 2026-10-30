package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.PeakMindTopBar
import com.example.ui.theme.LimeOnPrimary
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.PurpleAccent
import com.example.ui.theme.TealSecondary

data class SexualWellnessTopic(
    val title: String,
    val subtitle: String,
    val detailedAdvice: String,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun SexualWellnessScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var activeTopic by remember { mutableStateOf<SexualWellnessTopic?>(null) }

    val topics = listOf(
        SexualWellnessTopic(
            title = "Arousal Awareness",
            subtitle = "Understand your body & sensory cues",
            detailedAdvice = "Notice changes in breathing and unconscious pelvic tightening as excitement rises. Recognize the threshold level before the point of no return. Train yourself to pause, breathe into the abdomen, and release tension without forceful downward bearing.",
            icon = Icons.Default.Psychology,
            color = PurpleAccent
        ),
        SexualWellnessTopic(
            title = "Start-Stop Technique",
            subtitle = "Control & build functional endurance",
            detailedAdvice = "During private practice, gradually build stimulation. When approaching high arousal, pause all stimulation completely. Focus on deep abdominal breaths and pelvic relaxation. Once arousal subsides, resume slowly. Practice 2 to 3 cycles per session without performance anxiety.",
            icon = Icons.Default.Speed,
            color = LimePrimary
        ),
        SexualWellnessTopic(
            title = "Relaxation Breathing",
            subtitle = "Calm your autonomic nervous system",
            detailedAdvice = "Sympathetic overdrive (fight-or-flight) accelerates ejaculatory latency. Slow 4-second inhales and 6-second exhales activate parasympathetic vagal tone, lowering resting heart rate and restoring smooth muscle dilation.",
            icon = Icons.Default.Air,
            color = TealSecondary
        ),
        SexualWellnessTopic(
            title = "Partner Communication",
            subtitle = "Build comfort and intimacy",
            detailedAdvice = "Discuss goals, pacing, and comfort openly with your partner. Mutual understanding removes anxiety pressure and normalizes taking pauses. Intimacy extends far beyond rapid performance metrics.",
            icon = Icons.Default.People,
            color = Color(0xFFF472B6)
        ),
        SexualWellnessTopic(
            title = "ED Wellness & Conditioning",
            subtitle = "Aerobic fitness, lower body & vascular health",
            detailedAdvice = "Penile blood flow depends directly on endothelial nitric oxide release. Cardiovascular exercise (150 min/week), combined with squats, pelvic floor coordination, and waist reduction below 90 cm, significantly improves vascular responsiveness.",
            icon = Icons.Default.HealthAndSafety,
            color = Color(0xFF38BDF8)
        ),
        SexualWellnessTopic(
            title = "Progress Notes",
            subtitle = "Track your subjective consistency",
            detailedAdvice = "Keep private notes on your relaxation ease, stress levels, and comfort. Remember: Progress in pelvic conditioning and behavioral awareness is gradual. Never judge progress solely by stopwatch duration.",
            icon = Icons.Default.EditNote,
            color = Color(0xFFFBBF24)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("sexual_wellness_screen")
    ) {
        PeakMindTopBar(
            title = "Arousal Awareness",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // Behavioral Education Header Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, PurpleAccent.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Mind-Body Behavioral Support",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Evidence-based somatic awareness for voluntary relaxation, ejaculation control, and vascular confidence. Select any topic to review practical steps.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            topics.forEach { topic ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable { activeTopic = topic }
                        .testTag("wellness_topic_${topic.title.replace(" ", "_")}"),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(topic.color.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = topic.icon,
                                contentDescription = null,
                                tint = topic.color,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = topic.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = topic.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Detailed guidance dialog
        activeTopic?.let { topic ->
            AlertDialog(
                onDismissRequest = { activeTopic = null },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = topic.icon, contentDescription = null, tint = topic.color, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = topic.title, fontWeight = FontWeight.Bold)
                    }
                },
                text = {
                    Text(text = topic.detailedAdvice, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
                },
                confirmButton = {
                    Button(
                        onClick = { activeTopic = null },
                        colors = ButtonDefaults.buttonColors(containerColor = LimePrimary, contentColor = LimeOnPrimary)
                    ) {
                        Text("Understood", fontWeight = FontWeight.Bold)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}
