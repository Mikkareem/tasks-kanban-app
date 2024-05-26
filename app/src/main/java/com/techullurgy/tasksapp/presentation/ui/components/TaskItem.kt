package com.techullurgy.tasksapp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.domain.model.Task
import com.techullurgy.tasksapp.presentation.ui.theme.LocalChipColors
import com.techullurgy.tasksapp.presentation.ui.theme.TasksAppTheme

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = LocalContentColor.current.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val chipColor = LocalChipColors.current
                SmallChip(value = task.kanbanStatus.value, color = chipColor.green)
                Text(
                    text = task.createdOn,
                    style = MaterialTheme.typography.bodySmall,
                    color = LocalContentColor.current.copy(alpha = 0.5f)
                )
            }
        }
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