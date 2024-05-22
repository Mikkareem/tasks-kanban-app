package com.techullurgy.tasksapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.domain.model.Task
import com.techullurgy.tasksapp.ui.theme.TasksAppTheme

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = task.title, fontWeight = FontWeight.SemiBold)
            Text(text = task.description, color = LocalContentColor.current.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SmallChip(value = task.kanbanStatus.value, color = Color.Red)
                Text(text = "Created ${task.createdOn}", fontSize = 12.sp, color = LocalContentColor.current.copy(alpha = 0.5f))
            }
        }
    }
}

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

@Preview
@Composable
private fun TaskItemPreview() {
    TasksAppTheme {
        TaskItem(
            task = Task(
                title = "Cooking food",
                description = "Start cooking food at 10 AM Sharp",
                createdOn = "2 days ago",
                kanbanStatus = KanbanStatusType("WIP")
            )
        )
    }
}