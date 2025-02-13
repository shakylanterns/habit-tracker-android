package edu.cuhk.csci3310.ui.groupList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.cuhk.csci3310.data.Group

@Composable
fun GroupItem(
    group: Group,
    deleteGroup: (group: Group) -> Unit,
    changeGroup: (group: Group) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = group.name,
                color = Color(group.colour),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = group.description ?: "", style = MaterialTheme.typography.bodyLarge)
        }
        Row {
            IconButton(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary),
                onClick = {
                    deleteGroup(group)
                }
            ) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            IconButton(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                onClick = {
                    changeGroup(group)
                }
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}
